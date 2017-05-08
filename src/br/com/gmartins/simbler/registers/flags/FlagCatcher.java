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

/**
 * This class is used to check the values of the main register (AX).
 * For every value set on the AX, this class is called to check if it should enable or disable 
 * any flag (Overflow, Zero, Signal)
 * @author Guilherme
 */
public class FlagCatcher {

    private Value normalizedValue;
    private MainPanel panel;
    public FlagCatcher(MainPanel panel) {
        this.panel = panel;
    }

    /**
     * Este método faz a verificação do valor espeficiado e liga os sinalizadores que devem ser modificados.
     * @param valor Valor de AX ou o valor resultante do comando CMP
     */
    
    
    public void executeCheck(Value value) {
        this.normalizedValue = value;
        Signalizable zero = (Zero) panel.getRegisterManager().getZero();
        Signalizable signal = (Sinal) panel.getRegisterManager().getSignal();
        Signalizable overflow = (Overflow) panel.getRegisterManager().getOverflow();
        zero.setTurnedOn(false);
        signal.setTurnedOn(false);
        overflow.setTurnedOn(false);

        // Se o valor for igual a zero, liga o flag Zero
        if (value.isZero()) {
            zero.setTurnedOn(true);
        }
        // Se o valor for menor que zero, ou for "Zero negativo" (-0) - Liga o Flag Signal
        if (value.isSignal()) {
            signal.setTurnedOn(true);
        }

        if (value.isOverflow()) {
            overflow.setTurnedOn(true);
        }

        // Se o valor for maior que 127, ou menor que -127, liga o flag overflow
        if (value.toInteger() > 127) {
            this.normalizedValue = ALU.decimal(127);
        } else if (value.toInteger() < -127) {
            this.normalizedValue = ALU.decimal(-127);
        }
    }

    /**
     * This method is called when a value overflows the application memory.
     * This returns the maximum value or the minimum value of a registers.
     * @return 
     */
    public Value getNormalizedValue() {
        return this.normalizedValue;
    }
}
