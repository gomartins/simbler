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
import br.com.gmartins.simbler.components.JButtonToolTip;
import br.com.gmartins.simbler.properties.Text;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Register Panel
 * @author Guilherme
 */
public class PanelMemoryRegisters extends javax.swing.JPanel {

    /** Creates new form RegistersPanel */
    public PanelMemoryRegisters() {
        initComponents();
        getToolTipAX().setToolTipText(Text.get("registers.ax"));
        getToolTipBX().setToolTipText(Text.get("registers.default"));
        getToolTipCX().setToolTipText(Text.get("registers.default"));
        getToolTipDX().setToolTipText(Text.get("registers.default"));
        getToolTipPC().setToolTipText(Text.get("registers.pc"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image imagem = new ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/panel-registers-body.png")).getImage();
        g.drawImage(imagem, 0, 15, this);
    }

    /**
     * Get the AX Label
     * @return 
     */
    public ConverterLabel getLblAX() {
        return lblAX;
    }

    /**
     * Get the BX Label
     * @return 
     */
    public ConverterLabel getLblBX() {
        return lblBX;
    }

    /**
     * Get the CX Label
     * @return 
     */
    public ConverterLabel getLblCX() {
        return lblCX;
    }

    /**
     * Get the DX Label
     * @return 
     */
    public ConverterLabel getLblDX() {
        return lblDX;
    }

    /**
     * Get the PC Label
     * @return 
     */
    public ConverterLabel getLblPC() {
        return lblPC;
    }

    /**
     * Get the AX JToolTip
     * @return 
     */
    public JButtonToolTip getToolTipAX() {
        return toolTipAX;
    }

    /**
     * Get the BX JToolTip
     * @return 
     */
    public JButtonToolTip getToolTipBX() {
        return toolTipBX;
    }

    /**
     * Get the CX JToolTip
     * @return 
     */
    public JButtonToolTip getToolTipCX() {
        return toolTipCX;
    }

    /**
     * Get the DX JToolTip
     * @return 
     */
    public JButtonToolTip getToolTipDX() {
        return toolTipDX;
    }

    /**
     * Get the PC JToolTip
     * @return 
     */
    public JButtonToolTip getToolTipPC() {
        return toolTipPC;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblPC = new br.com.gmartins.simbler.components.ConverterLabel();
        lblBX = new br.com.gmartins.simbler.components.ConverterLabel();
        lblAX = new br.com.gmartins.simbler.components.ConverterLabel();
        lblDX = new br.com.gmartins.simbler.components.ConverterLabel();
        lblCX = new br.com.gmartins.simbler.components.ConverterLabel();
        toolTipDX = new br.com.gmartins.simbler.components.JButtonToolTip();
        toolTipPC = new br.com.gmartins.simbler.components.JButtonToolTip();
        toolTipBX = new br.com.gmartins.simbler.components.JButtonToolTip();
        toolTipCX = new br.com.gmartins.simbler.components.JButtonToolTip();
        toolTipAX = new br.com.gmartins.simbler.components.JButtonToolTip();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/panel-registers-title.png"))); // NOI18N

        lblPC.setBackground(new java.awt.Color(255, 255, 255));
        lblPC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        lblPC.setForeground(new java.awt.Color(0, 102, 153));
        lblPC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPC.setText("0");
        lblPC.setOpaque(true);

        lblBX.setBackground(new java.awt.Color(255, 255, 255));
        lblBX.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        lblBX.setForeground(new java.awt.Color(0, 102, 153));
        lblBX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBX.setText("0");
        lblBX.setOpaque(true);

        lblAX.setBackground(new java.awt.Color(255, 255, 255));
        lblAX.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        lblAX.setForeground(new java.awt.Color(0, 102, 153));
        lblAX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAX.setText("0");
        lblAX.setOpaque(true);

        lblDX.setBackground(new java.awt.Color(255, 255, 255));
        lblDX.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        lblDX.setForeground(new java.awt.Color(0, 102, 153));
        lblDX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDX.setText("0");
        lblDX.setOpaque(true);

        lblCX.setBackground(new java.awt.Color(255, 255, 255));
        lblCX.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
        lblCX.setForeground(new java.awt.Color(0, 102, 153));
        lblCX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCX.setText("0");
        lblCX.setOpaque(true);

        toolTipDX.setText("");
        toolTipDX.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        toolTipPC.setText("");
        toolTipPC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        toolTipBX.setText("");
        toolTipBX.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        toolTipCX.setText("");
        toolTipCX.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        toolTipAX.setText("");
        toolTipAX.setAlignmentY(0.0F);
        toolTipAX.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setText("CX:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("BX:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("AX:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setText("PC:");
        jLabel5.setInheritsPopupMenu(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setText("DX:");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLabel1)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel5)
                            .add(jLabel3)
                            .add(jLabel2)
                            .add(jLabel6))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, lblPC, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .add(lblBX, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, lblDX, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, lblCX, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(toolTipBX, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(toolTipCX, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(toolTipDX, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(toolTipPC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createSequentialGroup()
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lblAX, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(toolTipAX, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel1)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(toolTipAX, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(lblAX, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel4)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(lblBX, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel2))
                    .add(toolTipBX, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(lblCX, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel3))
                    .add(toolTipCX, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(lblDX, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel6))
                    .add(toolTipDX, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(lblPC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel5))
                    .add(toolTipPC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private br.com.gmartins.simbler.components.ConverterLabel lblAX;
    private br.com.gmartins.simbler.components.ConverterLabel lblBX;
    private br.com.gmartins.simbler.components.ConverterLabel lblCX;
    private br.com.gmartins.simbler.components.ConverterLabel lblDX;
    private br.com.gmartins.simbler.components.ConverterLabel lblPC;
    private br.com.gmartins.simbler.components.JButtonToolTip toolTipAX;
    private br.com.gmartins.simbler.components.JButtonToolTip toolTipBX;
    private br.com.gmartins.simbler.components.JButtonToolTip toolTipCX;
    private br.com.gmartins.simbler.components.JButtonToolTip toolTipDX;
    private br.com.gmartins.simbler.components.JButtonToolTip toolTipPC;
    // End of variables declaration//GEN-END:variables
}
