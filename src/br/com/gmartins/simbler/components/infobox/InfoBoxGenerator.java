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
package br.com.gmartins.simbler.components.infobox;

import br.com.gmartins.simbler.Principal;
import br.com.gmartins.simbler.properties.Text;

/**
 * Class used to help to generate the "InfoBox" dialogs
 * @author Guilherme
 */
public class InfoBoxGenerator {

    /**
     * Generate an error message when the user types a wrong syntax.
     * @param cmd the command name
     * @param line the line number
     * @param similarCommand a similar command
     */
    public static void showUnknownCommandDialog(String cmd, int line, String similarCommand) {
        InfoBox info = new InfoBox(Principal.getInstance(), true);
        info.setTitle(Text.get("error.infobox_ops"));
        info.setTitleMessage(Text.get("error.infobox_ops"));
        info.setFirstMessage(Text.get("error.infobox_address")+": " + line);
        info.setSecondMessage(Text.get("error.infobox_instruction")+": " + cmd);
        if (similarCommand != null && similarCommand.isEmpty() == false) {
            info.setThirdMessage(similarCommand);
        } else {
            info.setThirdMessage(Text.get("error.infobox_all_cmd"));
        }
        info.setVisible(true);
    }
}
