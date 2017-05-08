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
public class NOP extends Commands {

    public NOP(MainPanel main) {
        super(main);
    }

    @Override
    public void execute() {
        String regex0 = MnemonicsMap.getInstance().getMnemonicsMap().get("NOP").getRegexList().get(0).getRegex(true);
        /*
        MATCHES NOP
         */

        if (RegexMatcher.matches(regex0, getCurrentInstruction().getLineCommand())) {
            // Nop, não faz nada...
        }

    }
}
