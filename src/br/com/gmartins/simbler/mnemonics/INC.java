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
public class INC extends Commands {

    public INC(MainPanel main) {
        super(main);
    }

    @Override
    public void execute() {
        String regex0 = MnemonicsMap.getInstance().getMnemonicsMap().get("INC").getRegexList().get(0).getRegex(true);
        String regex1 = MnemonicsMap.getInstance().getMnemonicsMap().get("INC").getRegexList().get(1).getRegex(true);
        // MATCHES INC @10, INC @100
        // REGEX[0] - "INC @[0-9]{1,9}"
        // MATCHES INC AX, INC DX
        // REGEX[1] - "INC [ABCD]X"


        if (RegexMatcher.matches(regex0, getCurrentInstruction().getLineCommand())) {
            // Pega o comando na posição definida pelo numero após o @
            Instruction dest = getInstructionAt(getCurrentInstruction().getValue().toInteger());
            // Incrementa o valor em 1
            Value value = ALU.sum(dest.getValue(), ALU.decimal(1));
            // Substitui pelo novo valor
            dest.replaceValue(value, this.getMainPanel().getDataTypeInput().getDataType());
        } else if (RegexMatcher.matches(regex1, getCurrentInstruction().getLineCommand())) {
            // Incrementa o valor em 1
            Value value = ALU.sum(getCurrentInstruction().getRegister().getValue(), ALU.decimal(1));
            // Atribui o novo valor ao registrador especificado
            getCurrentInstruction().getRegister().setValue(value);
        }

    }
}
