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

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Guilherme
 */
public class EquivalencyTable {

    private Map<String, EquivalencyObject> mapa;

    private EquivalencyTable() {
        this.mapa = new HashMap<String, EquivalencyObject>();
        this.add("0", "0", "00000000");
        this.add("1", "1", "00000001");
        this.add("2", "2", "00000010");
        this.add("3", "3", "00000011");
        this.add("4", "4", "00000100");
        this.add("5", "5", "00000101");
        this.add("6", "6", "00000110");
        this.add("7", "7", "00000111");
        this.add("8", "8", "00001000");
        this.add("9", "9", "00001001");
        this.add("10", "A", "00001010");
        this.add("11", "B", "00001011");
        this.add("12", "C", "00001100");
        this.add("13", "D", "00001101");
        this.add("14", "E", "00001110");
        this.add("15", "F", "00001111");
        this.add("16", "10", "00010000");
        this.add("17", "11", "00010001");
        this.add("18", "12", "00010010");
        this.add("19", "13", "00010011");
        this.add("20", "14", "00010100");
        this.add("21", "15", "00010101");
        this.add("22", "16", "00010110");
        this.add("23", "17", "00010111");
        this.add("24", "18", "00011000");
        this.add("25", "19", "00011001");
        this.add("26", "1A", "00011010");
        this.add("27", "1B", "00011011");
        this.add("28", "1C", "00011100");
        this.add("29", "1D", "00011101");
        this.add("30", "1E", "00011110");
        this.add("31", "1F", "00011111");
        this.add("32", "20", "00100000");
        this.add("33", "21", "00100001");
        this.add("34", "22", "00100010");
        this.add("35", "23", "00100011");
        this.add("36", "24", "00100100");
        this.add("37", "25", "00100101");
        this.add("38", "26", "00100110");
        this.add("39", "27", "00100111");
        this.add("40", "28", "00101000");
        this.add("41", "29", "00101001");
        this.add("42", "2A", "00101010");
        this.add("43", "2B", "00101011");
        this.add("44", "2C", "00101100");
        this.add("45", "2D", "00101101");
        this.add("46", "2E", "00101110");
        this.add("47", "2F", "00101111");
        this.add("48", "30", "00110000");
        this.add("49", "31", "00110001");
        this.add("50", "32", "00110010");
        this.add("51", "33", "00110011");
        this.add("52", "34", "00110100");
        this.add("53", "35", "00110101");
        this.add("54", "36", "00110110");
        this.add("55", "37", "00110111");
        this.add("56", "38", "00111000");
        this.add("57", "39", "00111001");
        this.add("58", "3A", "00111010");
        this.add("59", "3B", "00111011");
        this.add("60", "3C", "00111100");
        this.add("61", "3D", "00111101");
        this.add("62", "3E", "00111110");
        this.add("63", "3F", "00111111");
        this.add("64", "40", "01000000");
        this.add("65", "41", "01000001");
        this.add("66", "42", "01000010");
        this.add("67", "43", "01000011");
        this.add("68", "44", "01000100");
        this.add("69", "45", "01000101");
        this.add("70", "46", "01000110");
        this.add("71", "47", "01000111");
        this.add("72", "48", "01001000");
        this.add("73", "49", "01001001");
        this.add("74", "4A", "01001010");
        this.add("75", "4B", "01001011");
        this.add("76", "4C", "01001100");
        this.add("77", "4D", "01001101");
        this.add("78", "4E", "01001110");
        this.add("79", "4F", "01001111");
        this.add("80", "50", "01010000");
        this.add("81", "51", "01010001");
        this.add("82", "52", "01010010");
        this.add("83", "53", "01010011");
        this.add("84", "54", "01010100");
        this.add("85", "55", "01010101");
        this.add("86", "56", "01010110");
        this.add("87", "57", "01010111");
        this.add("88", "58", "01011000");
        this.add("89", "59", "01011001");
        this.add("90", "5A", "01011010");
        this.add("91", "5B", "01011011");
        this.add("92", "5C", "01011100");
        this.add("93", "5D", "01011101");
        this.add("94", "5E", "01011110");
        this.add("95", "5F", "01011111");
        this.add("96", "60", "01100000");
        this.add("97", "61", "01100001");
        this.add("98", "62", "01100010");
        this.add("99", "63", "01100011");
        this.add("100", "64", "01100100");
        this.add("101", "65", "01100101");
        this.add("102", "66", "01100110");
        this.add("103", "67", "01100111");
        this.add("104", "68", "01101000");
        this.add("105", "69", "01101001");
        this.add("106", "6A", "01101010");
        this.add("107", "6B", "01101011");
        this.add("108", "6C", "01101100");
        this.add("109", "6D", "01101101");
        this.add("110", "6E", "01101110");
        this.add("111", "6F", "01101111");
        this.add("112", "70", "01110000");
        this.add("113", "71", "01110001");
        this.add("114", "72", "01110010");
        this.add("115", "73", "01110011");
        this.add("116", "74", "01110100");
        this.add("117", "75", "01110101");
        this.add("118", "76", "01110110");
        this.add("119", "77", "01110111");
        this.add("120", "78", "01111000");
        this.add("121", "79", "01111001");
        this.add("122", "7A", "01111010");
        this.add("123", "7B", "01111011");
        this.add("124", "7C", "01111100");
        this.add("125", "7D", "01111101");
        this.add("126", "7E", "01111110");
        this.add("127", "7F", "01111111");
        this.add("-0", "80", "10000000");
        this.add("-127", "81", "10000001");
        this.add("-126", "82", "10000010");
        this.add("-125", "83", "10000011");
        this.add("-124", "84", "10000100");
        this.add("-123", "85", "10000101");
        this.add("-122", "86", "10000110");
        this.add("-121", "87", "10000111");
        this.add("-120", "88", "10001000");
        this.add("-119", "89", "10001001");
        this.add("-118", "8A", "10001010");
        this.add("-117", "8B", "10001011");
        this.add("-116", "8C", "10001100");
        this.add("-115", "8D", "10001101");
        this.add("-114", "8E", "10001110");
        this.add("-113", "8F", "10001111");
        this.add("-112", "90", "10010000");
        this.add("-111", "91", "10010001");
        this.add("-110", "92", "10010010");
        this.add("-109", "93", "10010011");
        this.add("-108", "94", "10010100");
        this.add("-107", "95", "10010101");
        this.add("-106", "96", "10010110");
        this.add("-105", "97", "10010111");
        this.add("-104", "98", "10011000");
        this.add("-103", "99", "10011001");
        this.add("-102", "9A", "10011010");
        this.add("-101", "9B", "10011011");
        this.add("-100", "9C", "10011100");
        this.add("-99", "9D", "10011101");
        this.add("-98", "9E", "10011110");
        this.add("-97", "9F", "10011111");
        this.add("-96", "A0", "10100000");
        this.add("-95", "A1", "10100001");
        this.add("-94", "A2", "10100010");
        this.add("-93", "A3", "10100011");
        this.add("-92", "A4", "10100100");
        this.add("-91", "A5", "10100101");
        this.add("-90", "A6", "10100110");
        this.add("-89", "A7", "10100111");
        this.add("-88", "A8", "10101000");
        this.add("-87", "A9", "10101001");
        this.add("-86", "AA", "10101010");
        this.add("-85", "AB", "10101011");
        this.add("-84", "AC", "10101100");
        this.add("-83", "AD", "10101101");
        this.add("-82", "AE", "10101110");
        this.add("-81", "AF", "10101111");
        this.add("-80", "B0", "10110000");
        this.add("-79", "B1", "10110001");
        this.add("-78", "B2", "10110010");
        this.add("-77", "B3", "10110011");
        this.add("-76", "B4", "10110100");
        this.add("-75", "B5", "10110101");
        this.add("-74", "B6", "10110110");
        this.add("-73", "B7", "10110111");
        this.add("-72", "B8", "10111000");
        this.add("-71", "B9", "10111001");
        this.add("-70", "BA", "10111010");
        this.add("-69", "BB", "10111011");
        this.add("-68", "BC", "10111100");
        this.add("-67", "BD", "10111101");
        this.add("-66", "BE", "10111110");
        this.add("-65", "BF", "10111111");
        this.add("-64", "C0", "11000000");
        this.add("-63", "C1", "11000001");
        this.add("-62", "C2", "11000010");
        this.add("-61", "C3", "11000011");
        this.add("-60", "C4", "11000100");
        this.add("-59", "C5", "11000101");
        this.add("-58", "C6", "11000110");
        this.add("-57", "C7", "11000111");
        this.add("-56", "C8", "11001000");
        this.add("-55", "C9", "11001001");
        this.add("-54", "CA", "11001010");
        this.add("-53", "CB", "11001011");
        this.add("-52", "CC", "11001100");
        this.add("-51", "CD", "11001101");
        this.add("-50", "CE", "11001110");
        this.add("-49", "CF", "11001111");
        this.add("-48", "D0", "11010000");
        this.add("-47", "D1", "11010001");
        this.add("-46", "D2", "11010010");
        this.add("-45", "D3", "11010011");
        this.add("-44", "D4", "11010100");
        this.add("-43", "D5", "11010101");
        this.add("-42", "D6", "11010110");
        this.add("-41", "D7", "11010111");
        this.add("-40", "D8", "11011000");
        this.add("-39", "D9", "11011001");
        this.add("-38", "DA", "11011010");
        this.add("-37", "DB", "11011011");
        this.add("-36", "DC", "11011100");
        this.add("-35", "DD", "11011101");
        this.add("-34", "DE", "11011110");
        this.add("-33", "DF", "11011111");
        this.add("-32", "E0", "11100000");
        this.add("-31", "E1", "11100001");
        this.add("-30", "E2", "11100010");
        this.add("-29", "E3", "11100011");
        this.add("-28", "E4", "11100100");
        this.add("-27", "E5", "11100101");
        this.add("-26", "E6", "11100110");
        this.add("-25", "E7", "11100111");
        this.add("-24", "E8", "11101000");
        this.add("-23", "E9", "11101001");
        this.add("-22", "EA", "11101010");
        this.add("-21", "EB", "11101011");
        this.add("-20", "EC", "11101100");
        this.add("-19", "ED", "11101101");
        this.add("-18", "EE", "11101110");
        this.add("-17", "EF", "11101111");
        this.add("-16", "F0", "11110000");
        this.add("-15", "F1", "11110001");
        this.add("-14", "F2", "11110010");
        this.add("-13", "F3", "11110011");
        this.add("-12", "F4", "11110100");
        this.add("-11", "F5", "11110101");
        this.add("-10", "F6", "11110110");
        this.add("-9", "F7", "11110111");
        this.add("-8", "F8", "11111000");
        this.add("-7", "F9", "11111001");
        this.add("-6", "FA", "11111010");
        this.add("-5", "FB", "11111011");
        this.add("-4", "FC", "11111100");
        this.add("-3", "FD", "11111101");
        this.add("-2", "FE", "11111110");
        this.add("-1", "FF", "11111111");
    }

    private void add(String decimal, String hex, String binary) {
        EquivalencyObject equivalencyObject = new EquivalencyObject();
        equivalencyObject.setBinary(binary);
        equivalencyObject.setHex(hex);
        equivalencyObject.setDecimal(decimal);
        mapa.put(decimal, equivalencyObject);
    }

    /**
     * Retorna o objeto da tabela de conversão baseado no chave com o valor decimal.
     * @param decimal Valor decimal para recuperar o objeto.
     * @return Objeto recuperado.
     */
    public EquivalencyObject getDecimal(String decimal) {
        if (Integer.parseInt(decimal) > 127) {
            return mapa.get("127");
        } else if (Integer.parseInt(decimal) < -127) {
            return mapa.get("-127");
        }
        return mapa.get(decimal);
    }

    /**
     * Retorna um EquivalencyObject baseado no valor hexadecimal passado por parÃ¢metro.
     * Os valores hexadecimais comecam em 0 e vai até FF, qualquer valor fora desse range resultará num valor nulo.
     * @param hex Valor hexadecimal
     * @return 
     */
    public EquivalencyObject getHex(String hex) {
        // Verificar a possibilidade de otimizar a busca por valores do mapa, ao invés de percorrer a lista até encontrar um valor igual
        for (Map.Entry<String, EquivalencyObject> entry : mapa.entrySet()) {
            if (entry.getValue().getHex().equalsIgnoreCase(hex)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Retorna um EquivalencyObject baseado no valor binário passado por parÃ¢metro.
     * Os valores binários comecam em 00000000 e vai até 11111111, qualquer valor fora desse range resultará num valor nulo.
     * @param hex Valor binário
     * @return 
     */
    public EquivalencyObject getBinary(String binary) {
        // Verificar a possibilidade de otimizar a busca por valores do mapa, ao invés de percorrer a lista até encontrar um valor igual
        for (Map.Entry<String, EquivalencyObject> entry : mapa.entrySet()) {
            if (entry.getValue().getBinary().equalsIgnoreCase(binary)) {
                return entry.getValue();
            }
        }
        return null;
    }
    private static EquivalencyTable instance;

    public static EquivalencyTable getInstance() {
        if (instance == null) {
            instance = new EquivalencyTable();
        }

        return instance;
    }

    public class EquivalencyObject {

        private String hex;
        private String binary;
        private String decimal;

        public String getDecimal() {
            return decimal;
        }

        public void setDecimal(String decimal) {
            this.decimal = decimal;
        }

        public String getBinary() {
            return binary;
        }

        public String getHex() {
            return hex;
        }

        public void setBinary(String binary) {
            this.binary = binary;
        }

        public void setHex(String hex) {
            this.hex = hex;
        }
    }
}
