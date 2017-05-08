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

/**
 *
 * @author Guilherme
 */
public class JO extends Commands {

    public JO(MainPanel main) {
        super(main);
    }

    @Override
    public void execute() {
        final String workingCommand = "JO";
        String regex0 = MnemonicsMap.getInstance().getMnemonicsMap().get(workingCommand).getRegexList().get(0).getRegex(true);

        // MATCHES JO 10, JNO 500
        // REGEX[0] - "JO [0-9]{1,9}"};

        if (RegexMatcher.matches(regex0, getCurrentInstruction().getLineCommand())) {
            // Pega o valor do registrador Overflow
            int value = getRegisters().getOverflow().toInteger();
            // Se o valor do registrador Overflow estiver em "1", indica que a última operação estourou. Então pula.
            if (value == 1) {
                // Define a posição da linha com o valor definido no comando JO
                this.setLinePosition(getCurrentInstruction().getValue().toInteger());
            }
        }

    }
}
