/*
 * Simbler - Where Assembly becomes easy. Interactive and very easy, Simbler is 
 * a great tool to help students and interested people to learn and simulate 
 * the basics of Assembly Language.
 *
 * Copyright (C) 2011 Guilherme de Oliveira Martins
 * http://www.gmartins.com.br - guilherme@gmartins.com.br
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.gmartins.simbler.compiler.analyzers;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import br.com.gmartins.simbler.Principal;
import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.components.infobox.InfoBoxGenerator;
import br.com.gmartins.simbler.components.tooltip.TextHighlighter;
import br.com.gmartins.simbler.cpu.ALU;
import br.com.gmartins.simbler.helpers.LineValue;
import br.com.gmartins.simbler.helpers.RegexMatcher;
import br.com.gmartins.simbler.instructions.Instruction;
import br.com.gmartins.simbler.instructions.InstructionRegex;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicDetails;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicsMap;
import br.com.gmartins.simbler.properties.Text;

/**
 * Classe usada para análise léxica das instruções digitadas na área de código.
 *
 * @author Guilherme
 */
public class LexAnalyzerWorker extends SwingWorker<List<Instruction>, Integer> {

    /**
     * Aba atual do painel principal, de onde esta classe irá analisar as
     * instruções digitadas e recuperar todas informações necessárias
     */
    private MainPanel panel;

    /**
     * Lista de instruções geradas após a análise completa das instruções.
     */
    private List<Instruction> instructionsList;
    /**
     * Valor usado para definir se o código é válido ou não. Padrão: true
     */
    private boolean acceptable = true;
    /**
     * Indica se o programa deve executar e simular as operações após a análise
     * do código. Este valor é definido durante a construção da classe, se true,
     * caso as instruções estejam corretas, o programa irá executar e simular os
     * comandos digitados, se false, o programa irá apenas verificar as
     * instruções digitadas.
     */
    private boolean runAfterCheck;
    /**
     * Indica o texto no qual foi encontrado um erro no código do programa.
     */
    private String commandErrorName;
    /**
     * Indica o número da linha onde foi encontrado o erro no código do
     * programa.
     */
    private int lineError;
    /**
     * Barra de Progresso usada para mostrar o andamento da análise do código.
     */
    private JProgressBar progressBar;
    /**
     * Valor usado para definir se o código irá executar ou não no "Modo
     * Silencioso".
     */
    private boolean silentMode;
    /**
     * Highlighter usado para sublinhas o texto quando houver instruções
     * inválidas.
     */
    private TextHighlighter highlighter;

    public LexAnalyzerWorker(MainPanel mainPanel, boolean runAfterCheck) {
        this.panel = mainPanel;
        this.runAfterCheck = runAfterCheck;
        this.progressBar = Principal.getInstance().getProgressBar();
        //this.formattedLineList = new ArrayList<String>();
        highlighter = new TextHighlighter(this.panel.getCodeTextArea(), Color.red);
        highlighter.removeHighlights();
    }

    /**
     * Define se o código verificado é válido ou não.
     *
     * @param acceptable true para definir um código válido, false caso
     * contrário.
     */
    public void setAcceptable(boolean acceptable) {
        this.acceptable = acceptable;
    }

    /**
     * Verifica se o código analisado é válido ou não.
     *
     * @return true se o código for válido, false caso contrário.
     */
    public boolean isAcceptable() {
        return this.acceptable;
    }

    /**
     * Define se o analisador irá executar em modo silencioso. O Modo silencioso
     * evita com que o analisador mostre os erros encontrados durante a análise
     * do código. Ã‰ util para a verificação dos comandos durante a digitação do
     * usuário.
     *
     * @param silent true para ativar, false para desativar
     */
    public void setSilentMode(boolean silent) {
        this.silentMode = silent;
        this.runAfterCheck = false;
    }

    /**
     * Retorna o nome do comando que gerou o erro.
     *
     * @return
     */
    public String getCommandErrorName() {
        return commandErrorName;
    }

    /**
     * Define o nome do comando que gerou o erro
     *
     * @param commandName
     */
    public void setCommandErrorName(String commandName) {
        this.commandErrorName = commandName;
    }

    /**
     * Retorna a linha do comando que retornou o erro.
     *
     * @return
     */
    public int getLineError() {
        return lineError;
    }

    /**
     * Define a linha do comando que gerou o erro
     *
     * @param lineError
     */
    public void setLineError(int lineError) {
        this.lineError = lineError;
    }

    /**
     * Anexa um erro durante a verificação dos comandos para ser mostrado
     * posteriormente.
     *
     * @param commandName Nome da instrução que estava sendo verificada.
     * @param line Endereço da instrução.
     */
    private void appendError(String commandName, int line) {
        this.commandErrorName = commandName;
        this.lineError = line;
        this.setAcceptable(false);
    }

    /**
     * Habilita a barra de progresso.
     *
     * @param b true para habilitar, false para desabilitar
     * @param maxValue Valor máximo da barra de progresso.
     */
    public void setProgressVisible(boolean b, int maxValue) {
        this.progressBar.setVisible(b);
        this.progressBar.setString(Text.get("general.checking_commands"));
        this.progressBar.setStringPainted(b);
        this.progressBar.setMaximum(maxValue);
    }

    /**
     * Retorna o número total de linhas do painel que está sendo verificado.
     *
     * @return
     */
    public int getLineCount() {
        return panel.getCodeTextArea().getLineCount();
    }

    /**
     * Verifica palavra por palavra buscando algum problema na sintaxe do
     * código. Caso encontre algum erro, anexa um erro e retorna falso.
     *
     * @param line Linha de comando
     * @param formattedLine Construtor do novo comando formatado.
     * @param position posição atual da linha.
     * @return falso se houver algum comando inválido, verdadeiro caso
     * contrário.
     */
    private boolean checkWordByWord(String line, StringBuilder formattedLine, int position) {
        boolean isValid = true;

        // Remove os comentários
        if (line.contains(";")) {
            line = line.split(";")[0];
        }
        // Quebra toda a linha transformando-a em palavras.
        String[] words = line.split(InstructionRegex.RX_SPACES_BETWEEN);

        // Faz um laço percorrendo todas as palavras
        for (int j = 0; j < words.length; j++) {
            String word = words[j];
            // Verifica se a palavra é valida
            if (isWordValid(word) == false) {
                isValid = false;
                // Se estiver em modo silencioso, ignora e continua a execução do laço
                if (silentMode == false) {

                    // Se não estiver em modo silencioso, anexa o erro encontrado, 
                    // para a execução e mostra para o usuário o erro encontrado.
                    // Verifica se o comando é invalido, se for retorna.
                    this.appendError(word, position);
                    return false;
                }
            }
            // Coloca o comando
            formattedLine.append(word);

            // Se não for a última palavra, coloca um espaço no final
            if (j != words.length - 1) {
                formattedLine.append(" ");
            }
        }
        return isValid;
    }

    /**
     * Verifica se a palavra é um alfabeto válido dentro do programa. Aqui é
     * feito uma varredura de todas as palavras válidas, caso ela case com
     * alguma palavra, retorna true.
     *
     * Caso a palavra não seja encontrada, retorna false, indicando que o
     * usuário digitou algo que não existe no contexto do programa.
     *
     * @param word Palavra a ser verificada.
     * @return true se a palavra for válida, false caso contrário.
     */
    private boolean isWordValid(String word) {
        DataTypeRegex dataTypeRegex = new DataTypeRegex(this.panel);
        // Procura por registradores, se encontrar, retorna um comando válido.
        if (RegexMatcher.matches("@?[ABCD]X", word)) {
            return true;
        }

        // Pega o mapa de todos os comandos válidos do programa
        for (Map.Entry<String, MnemonicDetails> entry : MnemonicsMap.getInstance().getMnemonicsMap().entrySet()) {
            String regex = entry.getValue().getName();
            // Verifica se a palavra é igual ao nome da instrução, como ADD, STORE, LOAD e etc.
            // Se for, retorna true, por ser uma palavra válida.
            if (RegexMatcher.matches(regex, word)) {
                return true;
            }

            // Verifica se o nome da instrução é do tipo GeneralCommands.
            // Se for indica que pode ser um espaço em branco, ou um valor numérico.
            if (entry.getValue().getName().equals("GeneralCommands")) {
                // Então verifica se é um valor numérico, baseado no tipo de "Entrada de dados"
                // Selecionada pelo o usuário (Binário, Decimal ou Hexadecimal)
                if (RegexMatcher.matches(dataTypeRegex.getNumbersRegex(), word)) {
                    return true;
                } // Ou então, procura por linhas em branco
                else if (RegexMatcher.matches(entry.getValue().getRegexList().get(0).getRegex(false), word)) {
                    return true;
                }
            }
        }
        // Se não houver retornado true, no código acima, indica que nenhuma palavra válida foi encontrada
        // Então retorna false
        return false;
    }

    @Override
    protected List<Instruction> doInBackground() throws Exception {
        SyntacticAnalyzer analyzer = new SyntacticAnalyzer(panel);
        LineValue lineValueGetter = new LineValue();
        setProgressVisible(true, this.panel.getCodeTextArea().getLineCount());
        for (int position = 0; position < this.getLineCount(); position++) {
            String lineValue = lineValueGetter.getLineText(position, panel.getCodeTextArea());
            StringBuilder formattedLine = new StringBuilder();
            // Verifica palavra por palavra da linha digitada e monta
            // o StringBuilder devidamente formatado
            if (checkWordByWord(lineValue, formattedLine, position) == false) {
                if (silentMode == false) {
                    return null;
                }
            }
            String formatted = formattedLine.toString();

            // Analiza a sintaxe do comando formatado.
            analyzer.analyzeLine(formatted, position);
            if (analyzer.isAcceptable() == false) {
                if (silentMode) {
                    // Sublinha a linha que estiver com problema
                    highlighter.setHighlightedLine(position);
                    //this.formattedLineList.add("");
                    this.panel.getRegisterManager().getPc().setValue(ALU.decimal(position));
                    this.progressBar.setValue(position);
                    continue;
                } else {
                    this.appendError(formatted, position);
                    return null;
                }

            }

            // Atualiza o Registrador PC apenas se não estiver em SilentMode
            if (silentMode == false) {
                this.panel.getRegisterManager().getPc().setValue(ALU.decimal(position));
            }
            this.progressBar.setValue(position);
        }
        this.instructionsList = analyzer.getMnemonicList();

        this.setAcceptable(true);

        // this.cleanMemory();
       // this.allocateMemory();
        return instructionsList;
    }

    private void allocateMemory() {
        int size = instructionsList.size();
        int highestPosition = getHighestMemoryPosition();

        for (; size < highestPosition; size++) {
            instructionsList.add(this.addVoidInstruction(size));
            // Add empty line
            this.panel.getCodeTextArea().append("\n");
        }
    }

    private void cleanMemory() {  
        int size = instructionsList.size();
        int highestPosition = getHighestMemoryPosition();
        for (; size < highestPosition; size++) {
            instructionsList.remove(size);
            // Add empty line
            this.panel.getCodeTextArea().replaceTextOfLine(size, "", 0, 2);
        }
    }

    private Instruction addVoidInstruction(int pos) {
        Instruction instruction = new Instruction(panel);
        instruction.setPosition(pos);
        instruction.setMnemonic(MnemonicsMap.getInstance().getMnemonicsMap().get("GeneralCommands"));
        // Coloca o valor "0" artificialmente
        instruction.setValue(ALU.decimal(0));
        instruction.setEmpty(true);
        instruction.getValue().setValueStartPosition(0);
        instruction.getValue().setValueEndPosition(0);
        return instruction;
    }

    //pegar a quantidade maxima de linhas e comparar com a posic~ao maxima da memoria
    //preencher as lacunas com instrucoes vazias
    //completar com linhas em branco tbm
    //corrigor problema store @x para armazenar como DB etc
    private int getHighestMemoryPosition() {
        int highest = instructionsList.size();
        for (Instruction s : this.instructionsList) {
            String name = s.getMnemonic().getName();
            if (name != null
                    && (name.equals("JMP")
                    || name.equals("JNZ")
                    || name.equals("JZ"))) {
                if (s.getValue().toInteger() > highest) {
                    highest = s.getValue().toInteger();
                }
            } else if (s.hasLinks()) {
                // Any command that has @
                if (s.getValue().toInteger() > highest) {
                    highest = s.getValue().toInteger();
                }
            }
        }
        return highest;
    }

    @Override
    protected void done() {
        // Reseta o valor da barra de progresso
        this.setProgressVisible(false, 0);
        // Se o código não for válido
        if (isAcceptable() == false) {
            this.setAcceptable(false);
            SimilarSyntax similarCommands = new SimilarSyntax();
            final String similarCmd = similarCommands.getSimilar(commandErrorName);
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    InfoBoxGenerator.showUnknownCommandDialog(getCommandErrorName(), getLineError(), similarCmd);
                }
            });

        }

        if (silentMode) {
            panel.setCommandList(this.instructionsList);
        }

        if (this.runAfterCheck) {
            if (this.isAcceptable()) {
                panel.setSyntaxChecked(true);
                // Reseta o número de "PC"
                panel.getRegisterManager().getPc().setValue(ALU.decimal(0));
                panel.setCommandList(this.instructionsList);
               // System.out.println(this.getHighestMemoryPosition());
                // Método que se o AllowProgramRunning for true, inicia a execução do programa.
                panel.runProgram();
            }
        }

    }
}
