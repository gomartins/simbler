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

import br.com.gmartins.simbler.mnemonics.helpers.MnemonicsMap;
import br.com.gmartins.simbler.mnemonics.helpers.Commands;
import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.helpers.RegexMatcher;
import br.com.gmartins.simbler.instructions.Instruction;
import br.com.gmartins.simbler.instructions.value.Value;
import br.com.gmartins.simbler.cpu.ALU;

/**
 *
 * @author Guilherme
 */
public class OR extends Commands {

    public OR(MainPanel main) {
        super(main);
    }

    @Override
    public void execute() {
        final String workingCommand = "OR";
        String regex0 = MnemonicsMap.getInstance().getMnemonicsMap().get(workingCommand).getRegexList().get(0).getRegex(true);
        String regex1 = MnemonicsMap.getInstance().getMnemonicsMap().get(workingCommand).getRegexList().get(1).getRegex(true);
        String regex2 = MnemonicsMap.getInstance().getMnemonicsMap().get(workingCommand).getRegexList().get(2).getRegex(true);

        // MATCHES AND @1, AND @100
        //REGEX[0] - "AND @[0-9]{1,9}"
        // MATCHES AND 400, AND 300
        //REGEX[1] - "AND [0-9]{1,9}"
        // MATCHES AND AX, AND DX
        //REGEX[2] - "AND [ABCD]X"

        if (RegexMatcher.matches(regex0, getCurrentInstruction().getLineCommand())) {

            // Pega o valor do registrador AX
            Value value1 = getRegisters().getAx().getValue();
            // Pega o valor do comando na posição definida pelo numero após o @
            Value value2 = getInstructionAt(getCurrentInstruction().getValue()).getValue();

            // Faz o "AND" e define em AX
            getRegisters().getAx().setValue(ALU.or(value1, value2));
        } else if (RegexMatcher.matches(regex1, getCurrentInstruction().getLineCommand())) {
            // Pega o valor do registrador AX
            Value value1 = getRegisters().getAx().getValue();
            // Pega o valor da instrução atual
            Value value2 = getCurrentInstruction().getValue();
            // Faz o "AND" e define em AX
            getRegisters().getAx().setValue(ALU.or(value1, value2));
        } else if (RegexMatcher.matches(regex2, getCurrentInstruction().getLineCommand())) {
            // Pega o valor do registrador AX
            Value value1 = getRegisters().getAx().getValue();
            // Pega o valor do registrador da instrução
            Value value2 = getCurrentInstruction().getRegister().getValue();
            // Faz o "AND" e define em AX
            getRegisters().getAx().setValue(ALU.or(value1, value2));
        }

    }
}
