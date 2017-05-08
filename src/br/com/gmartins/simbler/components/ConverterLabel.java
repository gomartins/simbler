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
package br.com.gmartins.simbler.components;

import br.com.gmartins.simbler.components.datatype.DataType;
import br.com.gmartins.simbler.instructions.value.Value;
import javax.swing.JLabel;

/**
 * Customized JLabel to work with the default value system, that can 
 * be converted to the three numeric bases (binary, decimal and hexadecimal)
 * @author Guilherme
 */
public class ConverterLabel extends JLabel {

    /* The type of the data */
    private DataType dataType;

    public ConverterLabel() {
        /* Necessary to solve a problem that was happening with the PC register */
        dataType = DataType.DECIMAL;
    }

    /**
     * Set the type of the data.
     * @see DataType
     * @param type 
     */
    public void setConversionMode(DataType type) {
        this.dataType = type;
    }

    /**
     * Set the value of the Label.
     * @param value 
     */
    public void setText(Value value) {
        switch (dataType) {
            case DECIMAL:
                this.setText(value.toDecimal());
                break;
            case BINARY:
                this.setText(value.toBinary());
                break;
            case HEXADECIMAL:
                this.setText(value.toHex());
                break;
        }
    }
}
