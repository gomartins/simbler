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
package br.com.gmartins.simbler.registers;

import br.com.gmartins.simbler.components.ConverterLabel;
import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.instructions.value.Value;
import br.com.gmartins.simbler.properties.Text;
import br.com.gmartins.simbler.registers.flags.FlagCatcher;

/**
 *
 * @author Guilherme
 */
public class AX extends Registradores {

    private FlagCatcher flagCatcher;

    public AX(ConverterLabel lbl, MainPanel mainPanel) {
        super(mainPanel);
        flagCatcher = new FlagCatcher(mainPanel);
        this.setLabelComponent(lbl);
        this.setDescription(Text.get("registers.ax"));
    }

    @Override
    public void setValue(Value mnemonicValue) {
        // Estava causando NPE
        if (this.getMainPanel() != null) {
            flagCatcher.executeCheck(mnemonicValue);
        }
        
        super.setValue(flagCatcher.getNormalizedValue());
    }
}
