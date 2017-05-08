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
package br.com.gmartins.simbler.registers.flags;

import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.cpu.ALU;
import br.com.gmartins.simbler.instructions.value.Value;
import br.com.gmartins.simbler.registers.Registradores;
import javax.swing.JLabel;

/**
 * Flag Sinal.
 * 
 * This flag is activated when the last operation overflows the memory. (It is set to one)
 * Otherwise, it's set to zero.
 * 
 * Numbers > 127 and < -127 causes overflow.
 * 
 * @author Guilherme
 */
public class Overflow extends Registradores implements Signalizable {

    /**
     * Build a new register linked with the specified label.
     * @param lbl The linked label.
     */
    public Overflow(JLabel lbl, MainPanel panel) {
        super(panel);
        this.setLabelComponent(lbl);
    }

    /**
     * Turn on/off the Flag.
     * True to turn on, false to turn off.
     * This will automatically change the linked JLabel to 1 or 0.
     * @param b 
     */
    @Override
    public void setTurnedOn(boolean b) {
        if (b) {
            this.setValue(ALU.decimal(1));
            return;
        }
        this.setValue(ALU.decimal(0));
    }

    /**
     * Set the new value of the flag.
     * @param value the value
     */
    @Override
    public void setValue(Value value) {
        Integer valor = value.toInteger();
        if (valor != 1 && valor != 0) {
            throw new IllegalArgumentException("Flags só permitem valores 0 ou 1.");
        }
        super.setValue(value);
    }
}
