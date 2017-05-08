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
package br.com.gmartins.simbler.instructions.value;

import br.com.gmartins.simbler.components.datatype.DataType;
import br.com.gmartins.simbler.instructions.BaseConvertor;

/**
 * Classe usada para tratar valores dos comandos. Todos valores são armazenados
 * internamente como Strings. Na hora de ser mostrado ele é convertido para o
 * padrão de visualização selecionado.
 *
 * @author Guilherme
 */
public class Value {

    private String value;
    private DataType valueType;
    private boolean memoryDealer;
    private int valueStartPosition;
    private int valueEndPosition;
    private boolean signal;
    private boolean zero;
    private boolean overflow;

    /**
     * @param value Valor atual
     * @param valueType O tipo do valor atual (Binário / Decimal / Hexadecimal)
     */
    public Value(String value, DataType valueType) {
        this.setValue(value, valueType);
    }

    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }

    public void setZero(boolean zero) {
        this.zero = zero;
    }

    public boolean isOverflow() {
        return overflow;
    }

    public boolean isSignal() {
        return signal;
    }

    public boolean isZero() {
        return zero;
    }

    /**
     * Get the index of last value character Used for replacement purposes
     *
     * @return
     */
    public int getCaretEndPosition() {
        return valueEndPosition;
    }

    /**
     * Set the index of last value character
     *
     * @return
     */
    public void setValueEndPosition(int valueEndPosition) {
        this.valueEndPosition = valueEndPosition;
    }

    /**
     * Get the index of first value character Used for replacement purposes
     *
     * @return
     */
    public int getCaretStartPosition() {
        return valueStartPosition;
    }

    /**
     * Get the index of first value character
     *
     * @return
     */
    public void setValueStartPosition(int valueStartPosition) {
        this.valueStartPosition = valueStartPosition;
    }


    /**
     * Se o valor conter '@' ele é um MemoryDealer, isso significa que na
     * verdade ele está pegando um valor de outra posição na memória.
     *
     * @return
     */
    public boolean isMemoryDealer() {
        return memoryDealer;
    }

    public void setMemoryDealer(boolean memoryDealer) {
        this.memoryDealer = memoryDealer;
    }

    /**
     * Define o valor do comando. Todo comando deve ser passado como uma String.
     * Nesse momento é retirado o '@' (caso haja), e é guardado seu valor e seu
     * tipo para posteriormente ser recuperado e convertido corretamente.
     *
     * @param value
     */
    public final void setValue(String value, DataType valueType) {


        //if (valueType == ConverterLabel.DECIMAL_MODE) {
        //    setValueState(Integer.parseInt(value));
        //}

        if (value != null && value.contains("@")) {
            value = value.replace("@", "").trim();
            this.setMemoryDealer(true);
        }

        this.value = value;
        this.valueType = valueType;
    }

    /**
     * Retorna o valor na forma original que foi obtido.
     *
     * @return
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Retorna o tipo do valor.
     *
     * @return
     */
    public DataType getType() {
        return valueType;
    }

    /**
     * Define o tipo do valor. Os tipos estão definidos na classe DataType. O
     * tipo pode ser Binário, Decimal, ou Hexadecimal.
     *
     * @param valueType
     */
    public void setType(DataType valueType) {
        this.valueType = valueType;
    }

    /**
     * Converte o valor para decimal.
     *
     * @return
     */
    public String toDecimal() {
        BaseConvertor baseConvertor = new BaseConvertor();
        switch (this.valueType) {
            case BINARY:
                return baseConvertor.binToDec(this.value);
            case HEXADECIMAL:
                return baseConvertor.hexToDec(this.value);
            case DECIMAL:
                return value;
        }
        return null;
    }

    /**
     * Esse valor é usado como base para os cálculos efetuados durante a
     * execução do programa. Trata-se do valor original convertido para decimal,
     * com um tipo de retorno Inteiro para trabalhar com cálculos durante o
     * programa.
     *
     * @return
     */
    public int toInteger() {
        return Integer.parseInt(this.toDecimal());
    }

    /**
     * Converte o valor para Hexadecimal.
     *
     * @return
     */
    public String toHex() {
        BaseConvertor baseConvertor = new BaseConvertor();
        switch (this.valueType) {
            case BINARY:
                return baseConvertor.binToHex(this.value);
            case HEXADECIMAL:
                return value;
            case DECIMAL:
                return baseConvertor.decToHex(this.value);
        }
        return null;
    }

    /**
     * Converte o valor para binário.
     *
     * @return
     */
    public String toBinary() {
        BaseConvertor baseConvertor = new BaseConvertor();
        switch (this.valueType) {
            case BINARY:
                return this.value;
            case HEXADECIMAL:
                return baseConvertor.hexToBin(this.value);
            case DECIMAL:
                return baseConvertor.decToBin(this.value);
        }
        return null;
    }
}
