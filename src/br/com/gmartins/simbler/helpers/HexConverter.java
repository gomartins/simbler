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
package br.com.gmartins.simbler.helpers;

/**
 *
 * @author Guilherme
 */
public class HexConverter {

    private String hexValue;

    public void setHexValue(String hexValue) {
        this.hexValue = hexValue.toUpperCase();
    }

    public int convertToDecimal() {
        int finalValue = 0;
        char[] breakValue = hexValue.toCharArray();
        int j = breakValue.length - 1;
        for (int i = 0; i < breakValue.length; i++) {
            int tempValue = 0;
            if (!hexValue.equals("\n") || !hexValue.equals("") || !hexValue.equals("000")) {
                switch (breakValue[j]) {
                    case 'A':
                        tempValue = 10;
                        break;
                    case 'B':
                        tempValue = 11;
                        break;
                    case 'C':
                        tempValue = 12;
                        break;
                    case 'D':
                        tempValue = 13;
                        break;
                    case 'E':
                        tempValue = 14;
                        break;
                    case 'F':
                        tempValue = 15;
                        break;
                    default:
                        tempValue = Integer.parseInt(String.valueOf(breakValue[j]));
                        break;
                }
                finalValue += (Math.pow(16, i) * tempValue);
                j--;
            } else {
                finalValue = 0;
            }
        }
        return finalValue;
    }
}
