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
 * Classe usada para an�lise l�xica das instru��es digitadas na �rea de c�digo.
 *
 * @author Guilherme
 */
public class LexAnalyzerWorker extends SwingWorker<List<Instruction>, Integer> {

    /**
     * Aba atual do painel principal, de onde esta classe ir� analisar as
     * instru��es digitadas e recuperar todas informa��es necess�rias
     */
    private MainPanel panel;

    /**
     * Lista de instru��es geradas ap�s a an�lise completa das instru��es.
     */
    private List<Instruction> instructionsList;
    /**
     * Valor usado para definir se o c�digo � v�lido ou n�o. Padr�o: true
     */
    private boolean acceptable = true;
    /**
     * Indica se o programa deve executar e simular as opera��es ap�s a an�lise
     * do c�digo. Este valor � definido durante a constru��o da classe, se true,
     * caso as instru��es estejam corretas, o programa ir� executar e simular os
     * comandos digitados, se false, o programa ir� apenas verificar as
     * instru��es digitadas.
     */
    private boolean runAfterCheck;
    /**
     * Indica o texto no qual foi encontrado um erro no c�digo do programa.
     */
    private String commandErrorName;
    /**
     * Indica o n�mero da linha onde foi encontrado o erro no c�digo do
     * programa.
     */
    private int lineError;
    /**
     * Barra de Progresso usada para mostrar o andamento da an�lise do c�digo.
     */
    private JProgressBar progressBar;
    /**
     * Valor usado para definir se o c�digo ir� executar ou n�o no "Modo
     * Silencioso".
     */
    private boolean silentMode;
    /**
     * Highlighter usado para sublinhas o texto quando houver instru��es
     * inv�lidas.
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
     * Define se o c�digo verificado � v�lido ou n�o.
     *
     * @param acceptable true para definir um c�digo v�lido, false caso
     * contr�rio.
     */
    public void setAcceptable(boolean acceptable) {
        this.acceptable = acceptable;
    }

    /**
     * Verifica se o c�digo analisado � v�lido ou n�o.
     *
     * @return true se o c�digo for v�lido, false caso contr�rio.
     */
    public boolean isAcceptable() {
        return this.acceptable;
    }

    /**
     * Define se o analisador ir� executar em modo silencioso. O Modo silencioso
     * evita com que o analisador mostre os erros encontrados durante a an�lise
     * do c�digo. É util para a verifica��o dos comandos durante a digita��o do
     * usu�rio.
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
     * Anexa um erro durante a verifica��o dos comandos para ser mostrado
     * posteriormente.
     *
     * @param commandName Nome da instru��o que estava sendo verificada.
     * @param line Endere�o da instru��o.
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
     * @param maxValue Valor m�ximo da barra de progresso.
     */
    public void setProgressVisible(boolean b, int maxValue) {
        this.progressBar.setVisible(b);
        this.progressBar.setString(Text.get("general.checking_commands"));
        this.progressBar.setStringPainted(b);
        this.progressBar.setMaximum(maxValue);
    }

    /**
     * Retorna o n�mero total de linhas do painel que est� sendo verificado.
     *
     * @return
     */
    public int getLineCount() {
        return panel.getCodeTextArea().getLineCount();
    }

    /**
     * Verifica palavra por palavra buscando algum problema na sintaxe do
     * c�digo. Caso encontre algum erro, anexa um erro e retorna falso.
     *
     * @param line Linha de comando
     * @param formattedLine Construtor do novo comando formatado.
     * @param position posi��o atual da linha.
     * @return falso se houver algum comando inv�lido, verdadeiro caso
     * contr�rio.
     */
    private boolean checkWordByWord(String line, StringBuilder formattedLine, int position) {
        boolean isValid = true;

        // Remove os coment�rios
        if (line.contains(";")) {
            line = line.split(";")[0];
        }
        // Quebra toda a linha transformando-a em palavras.
        String[] words = line.split(InstructionRegex.RX_SPACES_BETWEEN);

        // Faz um la�o percorrendo todas as palavras
        for (int j = 0; j < words.length; j++) {
            String word = words[j];
            // Verifica se a palavra � valida
            if (isWordValid(word) == false) {
                isValid = false;
                // Se estiver em modo silencioso, ignora e continua a execu��o do la�o
                if (silentMode == false) {

                    // Se n�o estiver em modo silencioso, anexa o erro encontrado, 
                    // para a execu��o e mostra para o usu�rio o erro encontrado.
                    // Verifica se o comando � invalido, se for retorna.
                    this.appendError(word, position);
                    return false;
                }
            }
            // Coloca o comando
            formattedLine.append(word);

            // Se n�o for a �ltima palavra, coloca um espa�o no final
            if (j != words.length - 1) {
                formattedLine.append(" ");
            }
        }
        return isValid;
    }

    /**
     * Verifica se a palavra � um alfabeto v�lido dentro do programa. Aqui �
     * feito uma varredura de todas as palavras v�lidas, caso ela case com
     * alguma palavra, retorna true.
     *
     * Caso a palavra n�o seja encontrada, retorna false, indicando que o
     * usu�rio digitou algo que n�o existe no contexto do programa.
     *
     * @param word Palavra a ser verificada.
     * @return true se a palavra for v�lida, false caso contr�rio.
     */
    private boolean isWordValid(String word) {
        DataTypeRegex dataTypeRegex = new DataTypeRegex(this.panel);
        // Procura por registradores, se encontrar, retorna um comando v�lido.
        if (RegexMatcher.matches("@?[ABCD]X", word)) {
            return true;
        }

        // Pega o mapa de todos os comandos v�lidos do programa
        for (Map.Entry<String, MnemonicDetails> entry : MnemonicsMap.getInstance().getMnemonicsMap().entrySet()) {
            String regex = entry.getValue().getName();
            // Verifica se a palavra � igual ao nome da instru��o, como ADD, STORE, LOAD e etc.
            // Se for, retorna true, por ser uma palavra v�lida.
            if (RegexMatcher.matches(regex, word)) {
                return true;
            }

            // Verifica se o nome da instru��o � do tipo GeneralCommands.
            // Se for indica que pode ser um espa�o em branco, ou um valor num�rico.
            if (entry.getValue().getName().equals("GeneralCommands")) {
                // Ent�o verifica se � um valor num�rico, baseado no tipo de "Entrada de dados"
                // Selecionada pelo o usu�rio (Bin�rio, Decimal ou Hexadecimal)
                if (RegexMatcher.matches(dataTypeRegex.getNumbersRegex(), word)) {
                    return true;
                } // Ou ent�o, procura por linhas em branco
                else if (RegexMatcher.matches(entry.getValue().getRegexList().get(0).getRegex(false), word)) {
                    return true;
                }
            }
        }
        // Se n�o houver retornado true, no c�digo acima, indica que nenhuma palavra v�lida foi encontrada
        // Ent�o retorna false
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

            // Atualiza o Registrador PC apenas se n�o estiver em SilentMode
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
        // Se o c�digo n�o for v�lido
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
                // Reseta o n�mero de "PC"
                panel.getRegisterManager().getPc().setValue(ALU.decimal(0));
                panel.setCommandList(this.instructionsList);
               // System.out.println(this.getHighestMemoryPosition());
                // M�todo que se o AllowProgramRunning for true, inicia a execu��o do programa.
                panel.runProgram();
            }
        }

    }
}
