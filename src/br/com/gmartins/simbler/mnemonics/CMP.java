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
import br.com.gmartins.simbler.registers.flags.FlagCatcher;

/**
 *
 * @author Guilherme
 */
public class CMP extends Commands {

    public CMP(MainPanel main) {
        super(main);
    }

    @Override
    public void execute() {
        final String workingCommand = "CMP";
        String regex0 = MnemonicsMap.getInstance().getMnemonicsMap().get(workingCommand).getRegexList().get(0).getRegex(true);
        String regex1 = MnemonicsMap.getInstance().getMnemonicsMap().get(workingCommand).getRegexList().get(1).getRegex(true);
        String regex2 = MnemonicsMap.getInstance().getMnemonicsMap().get(workingCommand).getRegexList().get(2).getRegex(true);

        // MATCHES CMP @1, CMP @100
        //REGEX[0] - "CMP @[0-9]{1,9}"
        // MATCHES CMP 400, CMP 300
        //REGEX[1] - "CMP [0-9]{1,9}"
        // MATCHES CMP AX, CMP DX
        //REGEX[2] - "CMP [ABCD]X"

        FlagCatcher flagCatcher = new FlagCatcher(getMainPanel());

        if (RegexMatcher.matches(regex0, getCurrentInstruction().getLineCommand())) {
            // Pega o comando na posição definida pelo numero após o @
            Instruction dest = getInstructionAt(getCurrentInstruction().getValue());
            Value value = ALU.sub(getRegisters().getAx().getValue(), dest.getValue());
            // Faz a verificação do valor obtido e liga as flags necessárias
            flagCatcher.executeCheck(value);
        } else if (RegexMatcher.matches(regex1, getCurrentInstruction().getLineCommand())) {
            // Subtrai o valor definido no ADD com o valor atual de AX

            Value value = ALU.sub(getRegisters().getAx().getValue(), getCurrentInstruction().getValue());
            // Faz a verificação do valor obtido e liga as flags necessárias
            flagCatcher.executeCheck(value);
        } else if (RegexMatcher.matches(regex2, getCurrentInstruction().getLineCommand())) {
            // Subtrai o valor do registrador definido em ADD com o valor de AX
            Value value = ALU.sub(getRegisters().getAx().getValue(), getCurrentInstruction().getRegister().getValue());
            // Faz a verificação do valor obtido e liga as flags necessárias
            flagCatcher.executeCheck(value);
        }

    }
}
