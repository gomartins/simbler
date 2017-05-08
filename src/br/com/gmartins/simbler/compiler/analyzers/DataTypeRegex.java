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

import br.com.gmartins.simbler.components.MainPanel;

/**
 * Class used to help to identify what kind of numeric base is being used by the panel.
 * Once the type is identified it returns the respective regular expression.
 * @author Guilherme
 */
public class DataTypeRegex {

    private MainPanel panel;

    public DataTypeRegex(MainPanel panel) {
        this.panel = panel;
    }

    /* 
    -?@?(1[0-2][0-7]|[0-9]){1,2}        - Aceita de -127 a 127
    [0-1]{8}                            - Aceita binários de 00000000 a 11111111
    [0-9A-F]{1,2}                       - Hexadecimais de 00 A FF
     */
    public String getNumbersRegex() {
        switch (this.panel.getDataTypeInput().getDataType()) {
            case BINARY:
                return "@?[0-1]{8}";
            case DECIMAL:
                return "-?@?(12[0-7]|1[0-1][0-9]|[0-9]{1,2})"; //  Aceita de -127 a 127
            case HEXADECIMAL:
                return "@?[0-9A-F]{1,2}"; // - Hexadecimais de 00 A FF
        }
        return null;
    }
}
