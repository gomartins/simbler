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

/**
 *
 * @author Guilherme
 */
public class DB extends Commands {

    public DB(MainPanel main) {
        super(main);
    }

    @Override
    public void execute() {
        String regex0 = MnemonicsMap.getInstance().getMnemonicsMap().get("DB").getRegexList().get(0).getRegex(true);
        /*
        MATCHES ADD @10, ADD @500
        REGEX[0] - "ADD @[0-9]{1,9}"
        MATCHES ADD 10, ADD 500
        REGEX[1] - "ADD [0-9]{1,9}"
        MATCHES ADD AX, ADD DX
        REGEX[2] - "ADD [ABCD]X"
         */

    }
}
