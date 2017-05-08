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

import br.com.gmartins.simbler.components.ConverterLabel;
import br.com.gmartins.simbler.properties.Text;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Flag Panel
 * @author Guilherme
 */
public class PanelMemoryFlags extends javax.swing.JPanel {

    /**
     * Creates a new Flag Panel.
     */
    public PanelMemoryFlags() {
        initComponents();
        toolTipSignal.setToolTipText(Text.get("flags.signal"));
        toolTipOverflow.setToolTipText(Text.get("flags.overflow"));
        toolTipZero.setToolTipText(Text.get("flags.zero"));

    }

    /**
     * Get the Overflow Label.
     * @return 
     */
    public ConverterLabel getLblOverflow() {
        return lblOverflow;
    }

    /**
     * Get the Signal Label.
     * @return 
     */
    public ConverterLabel getLblSignal() {
        return lblSignal;
    }

    /**
     * Get the Zero Label.
     * @return 
     */
    public ConverterLabel getLblZero() {
        return lblZero;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image imagem = new ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/panel-flags-body.png")).getImage();
        g.drawImage(imagem, 0, 15, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        toolTipOverflow = new br.com.gmartins.simbler.components.JButtonToolTip();
        toolTipSignal = new br.com.gmartins.simbler.components.JButtonToolTip();
        toolTipZero = new br.com.gmartins.simbler.components.JButtonToolTip();
        lblOverflow = new br.com.gmartins.simbler.components.ConverterLabel();
        lblSignal = new br.com.gmartins.simbler.components.ConverterLabel();
        lblZero = new br.com.gmartins.simbler.components.ConverterLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/panel-flags-title.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setText("O:");
        jLabel7.setToolTipText("Overflow");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setText("S:");
        jLabel8.setToolTipText("Signal");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setForeground(new java.awt.Color(0, 102, 153));
        jLabel9.setText("Z:");
        jLabel9.setToolTipText("Zero");

        toolTipOverflow.setText("");
        toolTipOverflow.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        toolTipSignal.setText("");
        toolTipSignal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        toolTipZero.setText("");
        toolTipZero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblOverflow.setBackground(new java.awt.Color(255, 255, 255));
        lblOverflow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        lblOverflow.setForeground(new java.awt.Color(0, 102, 153));
        lblOverflow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOverflow.setText("0");
        lblOverflow.setOpaque(true);

        lblSignal.setBackground(new java.awt.Color(255, 255, 255));
        lblSignal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        lblSignal.setForeground(new java.awt.Color(0, 102, 153));
        lblSignal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSignal.setText("0");
        lblSignal.setOpaque(true);

        lblZero.setBackground(new java.awt.Color(255, 255, 255));
        lblZero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        lblZero.setForeground(new java.awt.Color(0, 102, 153));
        lblZero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblZero.setText("0");
        lblZero.setOpaque(true);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLabel1)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel7)
                    .add(jLabel8)
                    .add(jLabel9))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lblOverflow, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .add(lblSignal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .add(lblZero, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(toolTipSignal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(toolTipZero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(toolTipOverflow, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel7)
                        .add(lblOverflow, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(toolTipOverflow, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel8)
                        .add(lblSignal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(toolTipSignal, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(toolTipZero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel9)
                        .add(lblZero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private br.com.gmartins.simbler.components.ConverterLabel lblOverflow;
    private br.com.gmartins.simbler.components.ConverterLabel lblSignal;
    private br.com.gmartins.simbler.components.ConverterLabel lblZero;
    private br.com.gmartins.simbler.components.JButtonToolTip toolTipOverflow;
    private br.com.gmartins.simbler.components.JButtonToolTip toolTipSignal;
    private br.com.gmartins.simbler.components.JButtonToolTip toolTipZero;
    // End of variables declaration//GEN-END:variables
}
