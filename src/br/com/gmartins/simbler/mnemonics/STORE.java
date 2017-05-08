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
package br.com.gmartins.simbler.mnemonics;

import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.helpers.RegexMatcher;
import br.com.gmartins.simbler.instructions.Instruction;
import br.com.gmartins.simbler.mnemonics.helpers.Commands;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicsMap;

/**
 *
 * @author Guilherme
 */
public class STORE extends Commands {

    public STORE(MainPanel main) {
        super(main);
    }

    @Override
    public void execute() {
        String regex0 = MnemonicsMap.getInstance().getMnemonicsMap().get("STORE").getRegexList().get(0).getRegex(true);
        String regex1 = MnemonicsMap.getInstance().getMnemonicsMap().get("STORE").getRegexList().get(1).getRegex(true);
        String regex2 = MnemonicsMap.getInstance().getMnemonicsMap().get("STORE").getRegexList().get(2).getRegex(true);
        // MATCHES STORE @1, STORE @100
        // REGEX[0] - "STORE @[0-9]{1,9}"
        // MATCHES STORE @AX, STORE @BX
        // REGEX[1] - "STORE @[ABCD]X"
        // MATCHES STORE AX, STORE BX
        // REGEX[2] - "STORE [ABCD]X"


        if (RegexMatcher.matches(regex0, getCurrentInstruction().getLineCommand())) {
            // Pega o comando onde o valor deverá ser gravado
            Instruction dest = getInstructionAt(getCurrentInstruction().getValue());
            // Substitui o valor atual pelo valor de AX
            dest.replaceValue(getRegisters().getAx().getValue(), this.getMainPanel().getDataTypeInput().getDataType());
        } else if (RegexMatcher.matches(regex1, getCurrentInstruction().getLineCommand())) {
            // Pega o comando onde o valor deverá ser gravado
            Instruction dest = getInstructionAt(getCurrentInstruction().getRegister().getValue());
            // Substitui o valor atual pelo valor de AX
            dest.replaceValue(getRegisters().getAx().getValue(), this.getMainPanel().getDataTypeInput().getDataType());
        } else if (RegexMatcher.matches(regex2, getCurrentInstruction().getLineCommand())) {
            // Define no registrador especificado pelo STORE [ABC]X o valor atual de AX.
            
            getCurrentInstruction().getRegister().setValue(getRegisters().getAx().getValue());
        }

    }
}
