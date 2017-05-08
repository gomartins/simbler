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
import br.com.gmartins.simbler.cpu.ALU;
import br.com.gmartins.simbler.instructions.value.Value;
import javax.swing.JLabel;

/**
 *
 * @author Guilherme
 */
public class Registradores {

    // private Object value = 0;
    private JLabel component;
    private MainPanel mainPanel;
    private Value value = ALU.decimal(0);
    private String description;

    public Registradores(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    
   
    public void setDescription(String text) {
        this.description = text;

    }

    public String getDescription() {
        return description;
    }

    protected void setLabelComponent(JLabel component) {
        this.component = component;
    }

    public Value getValue() {
        return value;
    }

    /**
     * Define o valor do registrador.
     * Caso ocorra do valor ser nulo, ele é automaticamente definido em 0.
     * @param objValue 
     */
    public void setValue(Value objValue) {
        if (objValue == null) {
            this.value = ALU.decimal(0);
            component.setText(this.value.toDecimal());
            return;
        }
        this.value = objValue;
        if (component instanceof ConverterLabel) {
            // Used for Registers AX, BX, CX and DX
            ((ConverterLabel) component).setText(objValue);
        } else {
            // Used for Register PC, which stores only decimal 
            component.setText(objValue.toDecimal());
        }
    }

    public int toInteger() {
        return this.getValue().toInteger();
    }
}
