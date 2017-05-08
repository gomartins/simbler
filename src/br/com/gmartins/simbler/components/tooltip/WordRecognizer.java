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
package br.com.gmartins.simbler.components.tooltip;

import java.util.Map;

import br.com.gmartins.simbler.Principal;
import br.com.gmartins.simbler.compiler.analyzers.DataTypeRegex;
import br.com.gmartins.simbler.helpers.RegexMatcher;
import br.com.gmartins.simbler.instructions.Instruction;
import br.com.gmartins.simbler.instructions.value.Value;
import br.com.gmartins.simbler.mnemonics.helpers.CommandsHelper;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicDetails;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicsMap;

/**
 * This class is used to recognize and build the known data about the focused word
 * on the JTextArea.
 * This is an improvement used with JToolTips to help users to build the code.
 * @author Guilherme
 */
public class WordRecognizer extends CommandsHelper {

    private String word;
    private int linePosition;

    /**
     * Initializes a new WordRecognizer. 
     * @param word The word to be recognized
     * @param linePosition The position of the word
     */
    public WordRecognizer(String word, int linePosition) {
        super(Principal.getInstance().getMainPanel());
        this.word = word;
        this.linePosition = linePosition;
    }

    /**
     * Returns the defined word.
     * @return 
     */
    public String getWord() {
        return word;
    }

    /**
     * Return the known information about the focused word.
     * @return 
     */
    public String getKnownData() {
        /* If the known data is null, return null.
        It avoids that the JToolTip generates a "pixel" with nothing on it.
         */
        if (recognizeWord() == null || recognizeWord().length() == 0 || recognizeWord().toString().equals("null")) {
            return null;
        }
        return recognizeWord().toString();
    }

    /**
     * This method identifies the kind of instruction and generates the respective data.
     * @return 
     */
    private StringBuilder recognizeWord() {
        StringBuilder builder = new StringBuilder();
        Instruction instruction = null;
        try {
            instruction = getInstructionAt(linePosition);
        } catch (java.lang.IndexOutOfBoundsException ex) {
            //System.out.println("Falha ao recuperar instrução número " + linePosition);
            // Aqui poderia tentar chamar um "Silent Compile". Esse erro ocorre quando não consigo recuperar a instrução da linha, após da enter...
        }
        if (instruction != null) {
            if (isNumber()) {
                Value value = instruction.getValue();
                builder.append("<HTML>");
                builder.append("<center><b>Valor Numérico</b></center> ");
                builder.append("<br>");
                builder.append("<b>Binário:</b> ").append(value.toBinary());
                builder.append("<br>");
                builder.append("<b>Decimal:</b> ").append(value.toDecimal());
                builder.append("<br>");
                builder.append("<b>Hexadecimal:</b> ").append(value.toHex());
                builder.append("</HTML>");
            }

            if (isMemoryDealer()) {
                Instruction dest = getInstructionAt(instruction.getValue());

                // Ocorre quando o endereço após o @ não existe.
                if (dest == null) {
                    builder.append("<HTML>");
                    builder.append("<center><b>Endereço de Memória</b></center>");
                    builder.append("<br>");
                    builder.append("<b>Valor: Desconhecido</b>");
                    builder.append("</HTML>");
                    return builder;
                } else {
                    Value value = dest.getValue();
                    builder.append("<HTML>");
                    builder.append("<center><b>Endereço de Memória</b></center>");
                    builder.append("<br>");
                    builder.append("<b>Binário:</b> ").append(value.toBinary());
                    builder.append("<br>");
                    builder.append("<b>Decimal:</b> ").append(value.toDecimal());
                    builder.append("<br>");
                    builder.append("<b>Hexadecimal:</b> ").append(value.toHex());
                    builder.append("</HTML>");
                    builder.append("</HTML>");
                    return builder;
                }

            }
            if (isMnemonic() && (instruction.getMnemonic().getDescrption() != null && instruction.getMnemonic().getDescrption().isEmpty() == false)) {
                builder.append("<HTML>");
                builder.append(instruction.getMnemonic().getDescrption());
                builder.append("<br>");
                builder.append("</HTML>");
                return builder;
            }

            if (isRegister()) {
                builder.append("<HTML>");
                builder.append(instruction.getRegister().getDescription());
                builder.append("<br>");
                builder.append("</HTML>");

            }

        } else {
            return null;
        }
        return builder;
    }

    /**
     * Check if the focused word is an register.
     * @return true if yes, false otherwise.
     */
    public boolean isRegister() {
        return RegexMatcher.matches("@?[ABCD]X", this.getWord());
    }

    /**
     * Checks if the focused word is a reserved word (mnemonic).
     * @return true if yes, false otherwise
     */
    public boolean isMnemonic() {
        for (Map.Entry<String, MnemonicDetails> entry : MnemonicsMap.getInstance().getMnemonicsMap().entrySet()) {
            String regex = entry.getValue().getName();
            if (RegexMatcher.matches(regex, this.getWord())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the word contains '@'
     * @return true if yes, false otherwise
     */
    public boolean isMemoryDealer() {
        return this.getWord().contains("@");
    }

    /**
     * Check if the focused word in a number.
     * @return true if yes, false otherwise.
     */
    public boolean isNumber() {
        String number = this.getWord().replace("@", "").trim();
        DataTypeRegex dataTypeRegex = new DataTypeRegex(Principal.getInstance().getMainPanel());
        return RegexMatcher.matches(dataTypeRegex.getNumbersRegex(), number);
    }
}
