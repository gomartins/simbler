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
package br.com.gmartins.simbler.cpu;

import br.com.gmartins.simbler.components.datatype.DataType;
import br.com.gmartins.simbler.instructions.BaseConvertor;
import br.com.gmartins.simbler.instructions.value.Value;

/**
 * Unidade lógica aritmética (ULA) ou em inglÃªs Arithmetic Logic Unit (ALU) é a unidade do processador (CPU)
 * que executa as operações lógicas ou aritméticas referenciadas pelas instruções.
 * 
 * @author Guilherme
 */
public class ALU {

    private static Value finalValue(int number) {
        Value value = new Value(number + "", DataType.DECIMAL);
        // Se o valor for igual a zero, liga o flag Zero
        if (number == 0) {
            value.setZero(true);
        }
        // Se o valor for menor que zero, ou for "Zero negativo" (-0) - Liga o Flag Signal
        if (number < 0) {
            value.setSignal(true);
        }
        // Se o valor for maior que 127, ou menor que -127, liga o flag overflow
        if (number > 127) {
            value.setOverflow(true);
        } else if (number < -127) {
            value.setOverflow(true);
        }
        return value;
    }

    /**
     * This method do the multiplication arithmetic operation
     * eg.: value1 * value2
     * @param value1 
     * @param value2
     * @return the new value
     */
    public static Value mul(int value1, int value2) {
        return finalValue(value1 * value2);
    }

    public static Value decimal(int number) {
        return finalValue(number);
    }

    /**
     * This method do the subtraction arithmetic operation
     * eg.: value1 - value2
     * @param value1 
     * @param value2
     * @return the new value
     */
    public static Value sub(int value1, int value2) {
        return finalValue(value1 - value2);
    }

    /**
     * This method do the addition arithmetic operation
     * eg.: value1 + value2
     * @param value1 
     * @param value2
     * @return the new value
     */
    public static Value sum(int value1, int value2) {
        return finalValue(value1 + value2);
    }

    /**
     * This method do the division arithmetic operation
     * eg.: value1 / value2
     * @param value1 
     * @param value2
     * @return the new value
     */
    public static Value div(int value1, int value2) {
        return finalValue(value1 / value2);
    }

    /**
     * This method do the addition arithmetic operation
     * eg.: value1 + value2
     * @param value1 
     * @param value2
     * @return the new value
     */
    public static Value sum(Value value1, Value value2) {
        return sum(value1.toInteger(), value2.toInteger());
    }

    /**
     * This method do the subtraction arithmetic operation
     * eg.: value1 - value2
     * @param value1 
     * @param value2
     * @return the new value
     */
    public static Value sub(Value value1, Value value2) {
        return sub(value1.toInteger(), value2.toInteger());
    }

    /**
     * This method do the multiplication arithmetic operation
     * eg.: value1 * value2
     * @param value1 
     * @param value2
     * @return the new value
     */
    public static Value mul(Value value1, Value value2) {
        return mul(value1.toInteger(), value2.toInteger());
    }

    /**
     * This method do the division arithmetic operation
     * eg.: value1 / value2
     * @param value1 
     * @param value2
     * @return the new value
     */
    public static Value div(Value value1, Value value2) {
        return div(value1.toInteger(), value2.toInteger());
    }

    /**
     * This method do the NOT arithmetic operation
     * eg.: NOT 01010101 = 10101010 
     * @param value1 
     * @return the new value
     */
    public static Value not(Value value) {
        int val = value.toInteger();
        if (val == 0) {
            return finalValue(-1);
        } else {
            val = (val * -1) - 1;
            return finalValue(val);
        }
    }

    /**
     * This method do the AND arithmetic operation
     * eg.: and(11001100,01000100) = 01000100
     * @param value1 
     * @param value2
     * @return the new value
     */
    public static Value and(Value value1, Value value2) {
        String zeroFillFormat = "%08d";
        // Converte os números para binário e preenche-os com 0 a esquerda
        String val1 = String.format(zeroFillFormat, Integer.parseInt(value1.toBinary()));
        String val2 = String.format(zeroFillFormat, Integer.parseInt(value2.toBinary()));
        StringBuilder andValue = new StringBuilder();

        // Percorre todo o valor binário definido, caractere por caractere.
        // Adiciona 1 ao StringBuilder só quando a posição atual, ambos números binários forem 1
        // Se não, adiciona 0.
        for (int i = 0; i < val1.length(); i++) {
            if (val1.charAt(i) == '1' && val2.charAt(i) == '1') {
                andValue.append("1");
            } else {
                andValue.append("0");
            }
        }
        String andValueDecimal = new BaseConvertor().binToDec(andValue.toString());
        return finalValue(Integer.parseInt(andValueDecimal));
    }

    /**
     * This method do the OR arithmetic operation
     * eg.: and(11001100,01000100) = 11001100
     * @param value1 
     * @param value2
     * @return the new value
     */
    public static Value or(Value value1, Value value2) {
        String zeroFillFormat = "%08d";
        // Converte os números para binário e preenche-os com 0 a esquerda
        String val1 = String.format(zeroFillFormat, Integer.parseInt(value1.toBinary()));
        String val2 = String.format(zeroFillFormat, Integer.parseInt(value2.toBinary()));
        StringBuilder andValue = new StringBuilder();

        // Percorre todo o valor binário definido, caractere por caractere.
        // Adiciona 0 ao StringBuilder só quando a posição atual, ambos números binários forem 0
        // Se não, adiciona 1.
        for (int i = 0; i < val1.length(); i++) {
            if (val1.charAt(i) == '0' && val2.charAt(i) == '0') {
                andValue.append("0");
            } else {
                andValue.append("1");
            }
        }
        String andValueDecimal = new BaseConvertor().binToDec(andValue.toString());
        return finalValue(Integer.parseInt(andValueDecimal));
    }
}
