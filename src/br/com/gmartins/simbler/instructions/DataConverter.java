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
import java.util.List;

/**
 *
 * @author Guilherme
 */
public class DataConverter {

    private MainPanel panel;

    public DataConverter(MainPanel panel) {
        this.panel = panel;
    }

    public void covertTo(DataType type) {
        Builder cmdBuilder = new Builder(panel);
        List<Instruction> commandList = cmdBuilder.getCommandList();


        if (commandList != null && commandList.isEmpty() == false) {
            for (Instruction instruction : commandList) {
                // Só entra se:
                // O Valor for diferente de nulo
                // A instrução não for vazia
                // Se a instrução não conter um registrador (Se ela contém um registrador, não contém um número, então não precisa de conversão)
                if (instruction.getValue() != null && instruction.isEmpty() == false && instruction.getRegister() == null) {
                    instruction.replaceValue(instruction.getValue(), type);
                }
            }
        }

    }
}