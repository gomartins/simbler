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
package br.com.gmartins.simbler.menuhelper;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.BadLocationException;

import br.com.gmartins.simbler.Principal;
import br.com.gmartins.simbler.components.CodeTextArea;
import br.com.gmartins.simbler.helpers.LineValue;
import br.com.gmartins.simbler.helpers.RegexMatcher;
import br.com.gmartins.simbler.instructions.InstructionRegex;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicDetails;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicsMap;

/**
 *
 * @author Guilherme
 */
public class LinkUpdater {

    public static final int BACKSPACE_MODE = 0;
    public static final int ENTER_MODE = 1;
    private String[] cmdLinha;
    private CodeTextArea cmdArea;

    public LinkUpdater() {
        this.cmdArea = Principal.getInstance().getTextArea();
        // Coloca no cmdLinha todas as linhas do JTextArea
        this.cmdLinha = cmdArea.getText().split("\n");
    }

    public void update(int operationMode) {


        int valueToAdd = 0;

        if (operationMode == BACKSPACE_MODE) {
            valueToAdd = -1;
        } else if (operationMode == ENTER_MODE) {
            valueToAdd = 1;
        }
        // Guarda a Posição atual do Cursor de Texto
        int CaretPos = cmdArea.getCaretPosition();
        // Pega a Linha atual do JTextArea
        int LinePos = new LineValue().getCurrentLineNumber(cmdArea, cmdArea.getCaretPosition());
        // Faz um for varrendo todos comandos procurando por "JMP"
        for (int i = 0; i < cmdLinha.length; i++) {
            // Quebra a Linha em Comandos
            // Por exemplo - cmdLinha[i] = "JMP 5"
            // cmdWord[0] = "JMP"
            // cmdWord[1] = "5"


            cmdLinha[i] = cmdLinha[i].trim(); // Remove os espaços da String
            String[] cmdWord = cmdLinha[i].split(InstructionRegex.RX_SPACES_BETWEEN); // Divide a String em palavras (Separada todos os espaços)

            Map<String, MnemonicDetails> map = MnemonicsMap.getInstance().getMnemonicsMap();

            // Verifica se na lista de Mnemonicos existe algum comando com o nome capturado em cmdWord[0]
            if (map.containsKey(cmdWord[0])) {
                MnemonicDetails m = map.get(cmdWord[0]);

                // Se sim, atribui ele em "m" e faz um for em todas as expressÃµes disponÃ­veis desse comando
                for (InstructionRegex regex : m.getRegexList()) {

                    // Se a expressão for vinculável (definida em MnemicsRegex) e a expressão casar com o valor da minha (cmdLinha[i])
                    // Quer dizer que esse comando contém vÃ­nculos e deve ser alterado
                    if (regex.isLinkable() && RegexMatcher.matches(regex.getRegex(true) + InstructionRegex.RX_COMMENTS, cmdLinha[i])) {
                        //  Preciso de alguma forma identificar os comentarios e devolve-los corretamente apos a insercao do enter.
                        //        Procurar também uma forma de fazer o backspace.
                        String guardaComentarios = "";
                        if (cmdLinha[i].contains(";")) { // Se existirem comentários
                            guardaComentarios = RegexMatcher.getMatch(regex.getRegex(true) + InstructionRegex.RX_COMMENTS, cmdLinha[i]);
                            guardaComentarios = guardaComentarios.replaceAll(regex.getRegex(true), "");
                            // Remove de toda a linha os comentários
                            cmdLinha[i] = cmdLinha[i].replace(guardaComentarios, "");
                            cmdWord = cmdLinha[i].split(InstructionRegex.RX_SPACES_BETWEEN);
                        }
                        // Se a palavra depois do comando tiver @, é necessário trata-lo de devolve-lo corretamente ao valor alterado.
                        if (cmdWord[1].substring(0, 1).equals("@")) {
                            // Se a posicão da linha for menor ou igual a do vÃ­nculo, então é necessário atualiza-lo.
                            // Se a linha for anterior, é necessário atualizar pois todas as linhas posteriores serão afetadas com o enter.
                            // Se for igual, ele ainda faz parte da alteração do enter e deve ser atualizado. 
                            if (LinePos <= Integer.parseInt(cmdWord[1].substring(1))) {
                                int value = Integer.parseInt(cmdWord[1].substring(1)) + valueToAdd;
                                // Junta o JMP + o novo valor
                                cmdLinha[i] = m.getName() + " @" + value;
                            }
                        } // Se não, apenas acrescenta 1 ao seu valor
                        else {
                            if (LinePos <= Integer.parseInt(cmdWord[1])) {
                                int value = Integer.parseInt(cmdWord[1]) + valueToAdd;
                                // Junta o JMP + o novo valor
                                cmdLinha[i] = m.getName() + " " + value;
                            }
                        }

                        cmdLinha[i] += guardaComentarios;
                    }
                }
            }

        }
        // Limpa a area de texto e reescreve
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < cmdLinha.length; i++) {
            str.append(cmdLinha[i]);
            // Evita que seja inserida uma linha após o último comando.
            if (cmdLinha.length - 1 != i) {
                str.append("\n");
            }
        }

        cmdArea.setText(str.toString());


        try {
            CaretPos = cmdArea.getLineStartOffset(LinePos);
        } catch (BadLocationException ex) {
            Logger.getLogger(LinkUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (operationMode == ENTER_MODE) {
            cmdArea.insert("\n", CaretPos);
        } else if (operationMode == BACKSPACE_MODE) {
            LineValue lineValue = new LineValue();
            lineValue.getLineText(LinePos, cmdArea, false);
            cmdArea.replaceRange("", lineValue.getCaretPositionLineStart() - 1, lineValue.getCaretPositionLineEnd());
        }

        cmdArea.setCaretPosition(CaretPos);

    }
}
