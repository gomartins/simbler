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

import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.components.datatype.DataType;
import br.com.gmartins.simbler.instructions.value.Value;
import br.com.gmartins.simbler.mnemonics.helpers.Executable;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicDetails;
import br.com.gmartins.simbler.registers.Registradores;

/**
 *
 * @author Guilherme
 */
public class Instruction {

    private MnemonicDetails mnemonic;
    private Executable executable;
    private Value value;
    private boolean empty;
    private Registradores register;
    private String originalCommand;
    private int position;
    private boolean links;
    private MainPanel panel;

    public Instruction(MainPanel panel) {
        this.panel = panel;
    }
    
    

    /**
     * Set True if the instruction is linked to another instruction (contains @)
     * @param links 
     */
    public void setHasLinks(boolean links) {
        this.links = links;
    }

    /**
     * * Set True if the instruction is linked to another instruction (contains @)
     * @return 
     */
    public boolean hasLinks() {
        return links;
    }
    
    
    

    /**
     * Retorna a posi��o da linha do comando
     * @return 
     */
    public int getPosition() {
        return position;
    }

    /**
     * Define a posi��o da linha do comando
     * @param position 
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Define o execut�vel dessa instru��o. O execut�vel ser� chamado durante a execu��o do programa
     * para efetuar as tarefas do programa.
     * @param executable 
     */
    public void setExecutable(Executable executable) {
        this.executable = executable;
    }

    public Executable getExecutable() {
        return executable;
    }

    public void setMnemonic(MnemonicDetails mnemonic) {
        this.mnemonic = mnemonic;
    }

    public MnemonicDetails getMnemonic() {
        return mnemonic;
    }

    /**
     * Define o comando completo dessa instru��o.
     * @param cmd 
     */
    public void setCompleteCommand(String cmd) {
        this.originalCommand = cmd;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
        if (value == null) {
            this.setEmpty(true);
            return;
        }
        if (value.getValue().matches("[ABCD]X")) {
            switch (value.getValue().charAt(0)) {
//                corrigir isso, usar mainpanel
                case 'A':
                    setRegister(panel.getRegisterManager().getAx());
                    break;
                case 'B':
                    setRegister(panel.getRegisterManager().getBx());
                    break;
                case 'C':
                    setRegister(panel.getRegisterManager().getCx());
                    break;
                case 'D':
                    setRegister(panel.getRegisterManager().getDx());
                    break;
            }
        }

    }

    private void replaceLineValue(Object newValue) {
        // Se este comando n�o tiver um registrador, permite que troque o valor.
        if (this.getRegister() == null) {
            panel.getCodeTextArea().replaceTextOfLine(position, String.valueOf(newValue),
                    getValue().getCaretStartPosition(), // Necess�rio, o valor substituido estava aparecendo 1 caractere antes
                    getValue().getCaretEndPosition());
        }
    }

    /**
     * Substitui um valor de uma linha, e define o novo valor a este objeto.
     * Esse m�todo � �til para Instru��es do tipo "STORE @X", "INC @X".
     * Essas instru��es precisam lidar com substitui��o dos valores na �rea de c�digo. É o que esse m�todo faz.
     * É necess�rio passar o valor que ser� colocado na linha e o tipo de base num�rica ser� usada.
     * A base num�rica poder� ser Bin�ria, Decimal ou Hexadecimal. @see ConverterLabel
     * @param value Novo valor dessa instru��o
     * @param toType Tipo de base num�rica. Bin�ria, Decimal ou Hexadecimal
     */
    public void replaceValue(Value value, DataType toType) {
        String val = null;
        switch (toType) {
            case BINARY:
                val = value.toBinary();
                break;
            case HEXADECIMAL:
                val = value.toHex();
                break;
            case DECIMAL:
                val = value.toDecimal();
        }
        value.setValue(val, toType);
        this.replaceLineValue(val);
    }

    /**
     * Retorna o registrador utilizado pela instru��o.
     * @return 
     */
    public Registradores getRegister() {
        return register;
    }

    /**
     * Define o registrador que a instru��o utiliza.
     * Facilita o trabalho quando necess�rio a recupera��o dos valores de registradores.
     * @param register 
     */
    public void setRegister(Registradores register) {
        this.register = register;
    }

    /**
     * Retorna o comando original, antes de ser parseado pelo construtor de instru��es.
     * @return 
     */
    public String getLineCommand() {
        return this.originalCommand;
    }

    /**
     * Verifica se a instru��o � vazia, como uma linha em branco.
     * @return 
     */
    public boolean isEmpty() {
        return this.empty;
    }

    /**
     * Define uma instru��o como vazia Útil para linhas em branco.
     * @param empty 
     */
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
