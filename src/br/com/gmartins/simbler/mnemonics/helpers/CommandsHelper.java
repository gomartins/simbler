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
package br.com.gmartins.simbler.mnemonics.helpers;

import java.util.List;

import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.components.memory.PanelMemory;
import br.com.gmartins.simbler.instructions.Instruction;
import br.com.gmartins.simbler.instructions.value.Value;

/**
 *
 * @author Guilherme
 */
public abstract class CommandsHelper {

    private MainPanel mainPanel;
    //private RegistersInstances registradores;
    private List<Instruction> list;

    public CommandsHelper(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    protected void setLinePosition(int pos) {
        // Correção dos comandos que saltam
        this.mainPanel.setLinePosition(pos - 1);
    }

    protected PanelMemory getRegisters() {
        return this.mainPanel.getRegisterManager();
    }

    protected Instruction getCurrentInstruction() {
        return getInstructionAt(getLinePosition());
    }

    protected Instruction getInstructionAt(int lineNumber) {
        if (list == null) {
            list = mainPanel.getCommandList();
        }

        if (lineNumber > list.size() - 1) {
            return null;
        } else {
            return list.get(lineNumber);
        }

    }

    protected Instruction getInstructionAt(Value value) {
        return this.getInstructionAt(value.toInteger());
    }

    protected int getLinePosition() {
        return mainPanel.getLinePosition();
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }
}
