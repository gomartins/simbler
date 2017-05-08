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
package br.com.gmartins.simbler.compiler.analyzers;

import br.com.gmartins.simbler.mnemonics.helpers.MnemonicDetails;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicsMap;
import br.com.gmartins.simbler.properties.Text;
import java.util.Map;

/**
 * Class used with the error message popup to help to find the similar syntax when an error occurs.
 * @author Guilherme
 */
public class SimilarSyntax {

    /**
     * Try to find a similar known syntax based on the parameter.
     * @param cmd The word that will look for similar syntaxes
     * @return The similar syntax.
     */
    public String getSimilar(String cmd) {
        String similarCommand = this.checkSimilarCommands(cmd);
        return similarCommand;
    }

    private String checkSimilarCommands(String cmd) {
        for (Map.Entry<String, MnemonicDetails> entry : MnemonicsMap.getInstance().getMnemonicsMap().entrySet()) {
            String cmdName = entry.getKey();

            if (cmd.equals(cmdName)) {
                return Text.get("error.infobox_suggestion1");
            } else if (cmd.length() >= 2 && cmdName.contains(cmd)) {
                return Text.get("error.infobox_suggestion")+": " + entry.getKey() + "?";
            }
        }
        return "";
    }
}
