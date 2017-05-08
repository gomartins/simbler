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
package br.com.gmartins.simbler.instructions;

/**
 *
 * @author Guilherme
 */
public class BaseConvertor {

    private EquivalencyTable.EquivalencyObject getEquivalentWithDecimal(String decimal) {
        return EquivalencyTable.getInstance().getDecimal(decimal);
    }

    private EquivalencyTable.EquivalencyObject getEquivalentWithHex(String hex) {
        return EquivalencyTable.getInstance().getHex(hex);
    }

    private EquivalencyTable.EquivalencyObject getEquivalentWithBinary(String binary) {
        return EquivalencyTable.getInstance().getBinary(binary);
    }

    //convert decimal to hex
    public String decToHex(String dec) {
        return getEquivalentWithDecimal(dec).getHex();
    }

    //convert decimal to bin
    public String decToBin(String dec) {
        if (!isDecimal(dec)) {
            return "Invalid input!";
        }
        return getEquivalentWithDecimal(dec).getBinary();
    }

    //convert hex to decimal
    public String hexToDec(String hex) {
        return getEquivalentWithHex(hex).getDecimal();
    }

    //convert hex to bin
    public String hexToBin(String hex) {
        hex = hex.toUpperCase();
        if (!isHexaDecimal(hex)) {
            return "Invalid input!";
        }
        String decimal = hexToDec(hex);
        return getEquivalentWithDecimal(decimal).getBinary();
    }

    //is input represents a Binary number
    private boolean isBinary(String bin) {
        return bin.matches("[0|1]+");

    }

    //is input represents a Hexadecimal number
    private boolean isHexaDecimal(String bin) {
        return bin.toUpperCase().matches("[0-9|A-F]+");

    }

    //is input represents a decimal number
    private boolean isDecimal(String bin) {
        return bin.matches("-?[0-9]+");

    }

    public String binToDec(String bin) {
        return getEquivalentWithBinary(bin).getDecimal();
    }

    //convert bin to hex
    public String binToHex(String bin) {
        if (!isBinary(bin)) {
            return "Invalid input!";
        }
        String decimal = binToDec(bin);
        return getEquivalentWithDecimal(decimal).getHex();
    }
}
