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
package br.com.gmartins.simbler.components.memory;

import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.components.datatype.DataType;
import br.com.gmartins.simbler.cpu.ALU;
import br.com.gmartins.simbler.registers.*;
import br.com.gmartins.simbler.registers.flags.Overflow;
import br.com.gmartins.simbler.registers.flags.Sinal;
import br.com.gmartins.simbler.registers.flags.Zero;


/**
 * Panel used to hold the registers and flags panels.
 * @author Guilherme
 */
public class PanelMemory extends javax.swing.JPanel {

    private AX ax;
    private BX bx;
    private CX cx;
    private DX dx;
    private PC pc;
    private Overflow overflow;
    private Zero zero;
    private Sinal sinal;

    /**
     * Build a new Panel Memory
     */
    public PanelMemory(MainPanel panel) {
        initComponents();
        ax = new AX(registerPanel.getLblAX(), panel);
        bx = new BX(registerPanel.getLblBX(), panel);
        cx = new CX(registerPanel.getLblCX(), panel);
        dx = new DX(registerPanel.getLblDX(), panel);
        pc = new PC(registerPanel.getLblPC(), panel);
        overflow = new Overflow(flagsPanel1.getLblOverflow(), panel);
        zero = new Zero(flagsPanel1.getLblZero(), panel);
        sinal = new Sinal(flagsPanel1.getLblSignal(), panel);
        dataTypeSetter.setRegisterPanel(this);
        this.setDataType(DataType.BINARY);
    }

    /**
     * Get the AX register.
     * @return 
     */
    public Registradores getAx() {
        return this.ax;
    }

    /**
     * Get the BX register.
     * @return 
     */
    public Registradores getBx() {
        return this.bx;
    }

    /**
     * Get the CX register.
     * @return 
     */
    public Registradores getCx() {
        return this.cx;
    }

    /**
     * Get the DX register.
     * @return 
     */
    public Registradores getDx() {
        return this.dx;
    }

    /**
     * Get the PC register.
     * @return 
     */
    public PC getPc() {
        return this.pc;
    }

    
    /**
     * Get the Overflow flag.
     * @return 
     */
    public Registradores getOverflow() {
        return this.overflow;
    }

    /**
     * Get the Zero flag.
     * @return 
     */
    public Registradores getZero() {
        return this.zero;
    }

    /**
     * Get the Signal flag.
     * @return 
     */
    public Registradores getSignal() {
        return this.sinal;
    }

    /**
     * Set the working DataType of the register panels.
     * @return 
     */
    public void setDataType(DataType type) {
        registerPanel.getLblAX().setConversionMode(type);
        registerPanel.getLblBX().setConversionMode(type);
        registerPanel.getLblCX().setConversionMode(type);
        registerPanel.getLblDX().setConversionMode(type);
        registerPanel.getLblAX().setText(getAx().getValue());
        registerPanel.getLblBX().setText(getBx().getValue());
        registerPanel.getLblCX().setText(getCx().getValue());
        registerPanel.getLblDX().setText(getDx().getValue());



    }

    /**
     * Reset the the value of the register panels.
     */
    public void resetValues() {
        getAx().setValue(ALU.decimal(0));
        getBx().setValue(ALU.decimal(0));
        getCx().setValue(ALU.decimal(0));
        getDx().setValue(ALU.decimal(0));
        getPc().setValue(ALU.decimal(0));
        getOverflow().setValue(ALU.decimal(0));
        getSignal().setValue(ALU.decimal(0));
        getZero().setValue(ALU.decimal(0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dataTypeSetter = new br.com.gmartins.simbler.components.datatype.DataTypeOutputSetter();
        registerPanel = new br.com.gmartins.simbler.components.memory.PanelMemoryRegisters();
        flagsPanel1 = new br.com.gmartins.simbler.components.memory.PanelMemoryFlags();

        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dataTypeSetter, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(flagsPanel1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(registerPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(dataTypeSetter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(registerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flagsPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.gmartins.simbler.components.datatype.DataTypeOutputSetter dataTypeSetter;
    private br.com.gmartins.simbler.components.memory.PanelMemoryFlags flagsPanel1;
    private br.com.gmartins.simbler.components.memory.PanelMemoryRegisters registerPanel;
    // End of variables declaration//GEN-END:variables
}
