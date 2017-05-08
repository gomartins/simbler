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
package br.com.gmartins.simbler.instructions;

import br.com.gmartins.simbler.compiler.analyzers.LexAnalyzerWorker;
import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.components.datatype.DataType;
import br.com.gmartins.simbler.cpu.ALU;
import br.com.gmartins.simbler.helpers.LineValue;
import br.com.gmartins.simbler.helpers.RegexMatcher;
import br.com.gmartins.simbler.instructions.value.Value;
import br.com.gmartins.simbler.mnemonics.*;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicDetails;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicsMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Guilherme
 */
public class Builder {

    private MainPanel panel;
    private boolean invalid;

    public Builder() {
    }

    public Builder(MainPanel panel) {
        this.panel = panel;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    private Map<String, MnemonicDetails> getMnemonicMap() {
        return MnemonicsMap.getInstance().getMnemonicsMap();
    }

    public Instruction getBuiltInstruction(String line, MainPanel panel, int position) {

        this.panel = panel;
        Instruction instruction = new Instruction(panel);

        instruction.setPosition(position);
        instruction.setCompleteCommand(line);

        // Deve quebrar perfeiramente pois neste momento o comando deve estar corretamente formatado.
        // Foi retirado espaços duplicados, comentários e tudo que possa atrapalhar pelo analisador léxico.
        String[] words = line.split(" ");
        Map<String, MnemonicDetails> map = this.getMnemonicMap();

        DataType dataType = panel.getDataTypeInput().getDataType();

        if (words.length != 0) {
            // Se for números
            if (words.length == 1 && RegexMatcher.matches(InstructionRegex.RX_NUMBERS, words[0])) {
                instruction.setMnemonic(map.get("GeneralCommands"));
                instruction.setValue(new Value(words[0], dataType));

                // Se for números, define como a posição, a posição atual da linha
                // Como posição final é definido a posição inicial + o tamanaho do comando.
                // > 123 < Ex: InÃ­cio 0 Fim 3
                instruction.getValue().setValueStartPosition(0);
                instruction.getValue().setValueEndPosition(words[0].length());
                // System.out.println(line + ": Inicial " + m.getValuePosition().getCaretStartPosition() + " Final:" + m.getValuePosition().getCaretEndPosition());
                return instruction;
                // Se for linha em branco
            } else if (words.length == 1 && (words[0] == null || words[0].equals("\n") || words[0].isEmpty())) {
                instruction.setMnemonic(map.get("GeneralCommands"));
                // Coloca o valor "0" artificialmente
                instruction.setValue(ALU.decimal(0));
                instruction.setEmpty(true);
                instruction.getValue().setValueStartPosition(0);
                instruction.getValue().setValueEndPosition(0);
                return instruction;
            } else if (words.length == 1) { // Provavelmente este caso serve só para o HLT
                // Não define posições, HLT não precisa de substituições.
                instruction.setMnemonic(map.get(words[0]));
                appendExecutable(instruction);
                return instruction;
            } // Se nao for nenhum caso acima, entra nesse. (INC AX, LOAD 30, STORE BX)
            else if (words.length == 2) {
                instruction.setMnemonic(map.get(words[0]));
                instruction.setValue(new Value(words[1], dataType));
                LineValue lineValue = new LineValue();
                String completeInstruction = lineValue.getLineText(position, panel.getCodeTextArea());
                int[] valuePosition = getValuePosition(completeInstruction, words[0], words[1]);
                instruction.getValue().setValueStartPosition(valuePosition[0]);
                instruction.getValue().setValueEndPosition(valuePosition[1]);

                if (lineValue.getLineText(position, panel.getCodeTextArea(), false).contains("@")) {
                    instruction.setHasLinks(true);
                }

                appendExecutable(instruction);
            }

        }
        return instruction;
    }



    private int[] getValuePosition(String originalCommand, String mnemonic, String value) {
        // Alterado 26/06/11 - Estava casando o @ como parte do valor, e nas alterações de formato de números, o @ estava sumindo
        value = value.replace("@", "");

        String exp = "^" + mnemonic + "\\s+@?(" + value + ").*$";
        Pattern p = Pattern.compile(exp);
        Matcher m = p.matcher(originalCommand);
        if (m.matches()) {
            return new int[]{m.start(1), m.end(1)};
        }
        return null;

    }

    public List<Instruction> getCommandList() {
        try {

            // Existe algum problema aquiii! Está travando
            LexAnalyzerWorker worker = new LexAnalyzerWorker(panel, false);
            worker.execute();
            while (worker.isDone() == false) {
                if (worker.isAcceptable()) {

                    return worker.get();

                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Acho que não tem como correr desse monte de if.
    // Preciso indiciar qual classe deve ser executada ao encontrar esse comando
    private void appendExecutable(Instruction mnemonicDetails) {

        if (mnemonicDetails.getMnemonic().getName().equals("ADD")) {
            mnemonicDetails.setExecutable(new ADD(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("DEC")) {
            mnemonicDetails.setExecutable(new DEC(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("DIV")) {
            mnemonicDetails.setExecutable(new DIV(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("HLT")) {
            mnemonicDetails.setExecutable(new HLT(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("INC")) {
            mnemonicDetails.setExecutable(new INC(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("JMP")) {
            mnemonicDetails.setExecutable(new JMP(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("JNO")) {
            mnemonicDetails.setExecutable(new JNO(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("JNS")) {
            mnemonicDetails.setExecutable(new JNS(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("JNZ")) {
            mnemonicDetails.setExecutable(new JNZ(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("JO")) {
            mnemonicDetails.setExecutable(new JO(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("JS")) {
            mnemonicDetails.setExecutable(new JS(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("JZ")) {
            mnemonicDetails.setExecutable(new JZ(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("LOAD")) {
            mnemonicDetails.setExecutable(new LOAD(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("MUL")) {
            mnemonicDetails.setExecutable(new MUL(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("STORE")) {
            mnemonicDetails.setExecutable(new STORE(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("SUB")) {
            mnemonicDetails.setExecutable(new SUB(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("NOT")) {
            mnemonicDetails.setExecutable(new NOT(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("NOP")) {
            mnemonicDetails.setExecutable(new NOP(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("AND")) {
            mnemonicDetails.setExecutable(new AND(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("OR")) {
            mnemonicDetails.setExecutable(new OR(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("CMP")) {
            mnemonicDetails.setExecutable(new CMP(panel));
        } else if (mnemonicDetails.getMnemonic().getName().equals("DB")) {
            mnemonicDetails.setExecutable(new DB(panel));
        }
    }
}
