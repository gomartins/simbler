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
import br.com.gmartins.simbler.cpu.ALU;
import br.com.gmartins.simbler.helpers.RegexMatcher;
import br.com.gmartins.simbler.instructions.Instruction;
import br.com.gmartins.simbler.instructions.value.Value;
import br.com.gmartins.simbler.mnemonics.helpers.Commands;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicsMap;

/**
 *
 * @author Guilherme
 */
public class ADD extends Commands {

    public ADD(MainPanel main) {
        super(main);
    }

    @Override
    public void execute() {
        String regex0 = MnemonicsMap.getInstance().getMnemonicsMap().get("ADD").getRegexList().get(0).getRegex(true);
        String regex1 = MnemonicsMap.getInstance().getMnemonicsMap().get("ADD").getRegexList().get(1).getRegex(true);
        String regex2 = MnemonicsMap.getInstance().getMnemonicsMap().get("ADD").getRegexList().get(2).getRegex(true);
        /*
        MATCHES ADD @10, ADD @500
        REGEX[0] - "ADD @[0-9]{1,9}"
        MATCHES ADD 10, ADD 500
        REGEX[1] - "ADD [0-9]{1,9}"
        MATCHES ADD AX, ADD DX
        REGEX[2] - "ADD [ABCD]X"
         */


        if (RegexMatcher.matches(regex0, getCurrentInstruction().getLineCommand())) {
            // Pega o comando na posição definida pelo numero após o @
            Instruction dest = getInstructionAt(getCurrentInstruction().getValue().toInteger());
            // Soma o valor definido no ADD com o valor atual de AX
            Value value = ALU.sum(getRegisters().getAx().getValue(), dest.getValue());
            // Define em AX o novo valor.
            getRegisters().getAx().setValue(value);
        } else if (RegexMatcher.matches(regex1, getCurrentInstruction().getLineCommand())) {
            // Soma o valor definido no ADD com o valor atual de AX
            Value value = ALU.sum(getRegisters().getAx().getValue(), getCurrentInstruction().getValue());
            // Define o novo valor no AX
            getRegisters().getAx().setValue(value);
        } else if (RegexMatcher.matches(regex2, getCurrentInstruction().getLineCommand())) {
            // Soma o valor do registrador definido em ADD com o valor de AX
            Value value = ALU.sum(getRegisters().getAx().getValue(), getCurrentInstruction().getRegister().getValue());
            // Define o novo valor em AX
            getRegisters().getAx().setValue(value);
        }

    }
}
