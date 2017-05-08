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
package br.com.gmartins.simbler.helpers;

import javax.swing.SwingWorker;
import javax.swing.text.BadLocationException;

import br.com.gmartins.simbler.Principal;
import br.com.gmartins.simbler.compiler.analyzers.SyntacticAnalyzer;
import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.cpu.ALU;
import br.com.gmartins.simbler.instructions.InstructionRegex;

public class Worker extends SwingWorker<Integer, Integer> {

    private MainPanel mainPanel;

    public Worker(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    private void HighlightLine(int LinePos) {
        try {
            mainPanel.getCodeTextArea().setCaretPosition(mainPanel.getCodeTextArea().getLineStartOffset(LinePos));
            // Caso entre no catch (o que não deve acontecer)
            // Faz com que a primeira linha seja selecionada
        } catch (BadLocationException ex) {
            mainPanel.getCodeTextArea().setCaretPosition(0);
        }
    }

    @Override
    protected Integer doInBackground() throws Exception {
        mainPanel.setFinished(false);
        int lineCount = mainPanel.getCodeTextArea().getLineCount();

        if (mainPanel.isPaused() == false) {
            while (mainPanel.getLinePosition() < lineCount) {

                // HighlightLine desativado na velocidade 0 por questÃµes de performance
                // Melhora a velocidade de execução
                if (mainPanel.getSpeed() != 0 || mainPanel.isStepByStepMode()) {
                    HighlightLine(mainPanel.getLinePosition());
                }

                // Troca no Registrador PC a Posição da Linha (LinePos)
                mainPanel.getRegisterManager().getPc().setValue(ALU.decimal(mainPanel.getLinePosition()));
                // Faz a verificação do comando da Linha [LinePos]
//                mainPanel.executeCommand(lineValue.getLineValue(mainPanel.getLinePosition(), mainPanel.getCodeTextArea(), false)); // passa a linha para a verificaçao dos comandos
                mainPanel.executeCommand(mainPanel.getLinePosition()); // passa a linha para a verificaçao dos comandos

                // Passa pelo Thread.sleep apenas se não for o modo passo-a-passo
                if (mainPanel.isStepByStepMode() == false) {
                    // Faz com que a Thread durma pelo tempo determinado por Speed
                    try {
                        Thread.sleep(mainPanel.getSpeed());
                    } catch (InterruptedException ex) {
                        // Logger.getLogger(NewJFrame1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // Incrementa a posicao da linha
                mainPanel.setLinePosition(mainPanel.getLinePosition() + 1);

                // Se o botão passo a passo for pressionado, após executar uma vez a rotina pausa e reseta o passo a passo

                if (mainPanel.isStepByStepMode()) {
                    mainPanel.setPause(true);

                    // Redefine StepByStep para falso, caso o usuário aperte o Executar convencional a seguir, não pare novamente.
                    mainPanel.setStepByStepMode(false);

                } else {
                	updateReferences();
                }
                // Solução para o problema que estava causando loop na aplicação...
                // Quando o botão parar era apertado a Thread nao parava...
                if (mainPanel.isPaused()) {
                    break;
                }

            } // Fim while


        } // Fim if (!pause)

        return 0;
    }

    @Override
    protected void done() {
// Apenas define o código como terminado se a posicão atual do programa for igual ao total de linhas...
        // Esta informação é util para os botão de Iniciar o código.
        if (mainPanel.getLinePosition() == mainPanel.getCodeTextArea().getLineCount()) {
            mainPanel.setFinished(true);
        }
        Principal.getInstance().repaint(); // JTextArea estava sobrepondo o JFrame quando haviam muitas linhas, o repaint corrigiu o problema.
    }
    
    
    
    
    
    
    
    private void updateReferences() {
        SyntacticAnalyzer analyzer = new SyntacticAnalyzer(mainPanel);
        LineValue lineValueGetter = new LineValue();
        for (int position = 0; position < mainPanel.getCodeTextArea().getLineCount(); position++) {
            String lineValue = lineValueGetter.getLineText(position, mainPanel.getCodeTextArea());
            StringBuilder formattedLine = new StringBuilder();
            // Verifica palavra por palavra da linha digitada e monta
            // o StringBuilder devidamente formatado
           checkWordByWord(lineValue, formattedLine, position);
            String formatted = formattedLine.toString();
            // Analiza a sintaxe do comando formatado.
            analyzer.analyzeLine(formatted, position);
        }
        
        mainPanel.setCommandList(analyzer.getMnemonicList());

    }
    
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
            // Coloca o comando
            formattedLine.append(word);

            // Se não for a última palavra, coloca um espaço no final
            if (j != words.length - 1) {
                formattedLine.append(" ");
            }
        }
        return isValid;
    }

    
    
    
    
}
