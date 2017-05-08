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
package br.com.gmartins.simbler.components.datatype;

import br.com.gmartins.simbler.components.memory.PanelMemory;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataTypeOutputSetter extends DataTypeComponent {

    private PanelMemory panel;

    public DataTypeOutputSetter() {
        initComponents();
        getPopupMenu().setPreferredSize(new Dimension(169, 60));
        this.setDataType(DataType.BINARY);
        this.setMenuItemAction(menuAction);
    }

    public void setRegisterPanel(PanelMemory panel) {
        this.panel = panel;
    }

    @Override
    public String getBackgroundImage() {
        return "/br/com/gmartins/simbler/images/btn-datatype-output.png";
    }

    @Override
    public void onClickEvent(ActionEvent e) {
        if (panel != null) {
            btnShowMenu.setText(e.getActionCommand());
            if (e.getActionCommand().equals(DataType.BINARY.getName())) {
                panel.setDataType(DataType.BINARY);
            } else if (e.getActionCommand().equals(DataType.DECIMAL.getName())) {
                panel.setDataType(DataType.DECIMAL);
            } else if (e.getActionCommand().equals(DataType.HEXADECIMAL.getName())) {
                panel.setDataType(DataType.HEXADECIMAL);
            }

            super.onClickEvent(e);
        }

    }
    private ActionListener menuAction = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            onClickEvent(e);
        }
    };

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnShowMenu = new javax.swing.JButton();

        setOpaque(false);

        btnShowMenu.setBackground(new java.awt.Color(51, 51, 51));
        btnShowMenu.setFont(new java.awt.Font("Tahoma", 1, 11));
        btnShowMenu.setForeground(new java.awt.Color(51, 51, 51));
        btnShowMenu.setText("Binário");
        btnShowMenu.setToolTipText("Tipo de Saída");
        btnShowMenu.setBorderPainted(false);
        btnShowMenu.setContentAreaFilled(false);
        btnShowMenu.setFocusable(false);
        btnShowMenu.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnShowMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnShowMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnShowMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowMenuActionPerformed
        getPopupMenu().show(this, 0, 36);
    }//GEN-LAST:event_btnShowMenuActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnShowMenu;
    // End of variables declaration//GEN-END:variables
}
