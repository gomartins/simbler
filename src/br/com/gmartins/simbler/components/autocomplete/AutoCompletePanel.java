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
package br.com.gmartins.simbler.components.autocomplete;

import br.com.gmartins.simbler.Principal;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicDetails;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicsMap;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Guilherme
 */
public class AutoCompletePanel extends javax.swing.JPanel {
    private static AutoCompletePanel instance;

    /** Creates new form Documentacao */
    private AutoCompletePanel() {
        initComponents();
        this.mnemonicList.addListSelectionListener(new SelectionListener());
    }

    public static AutoCompletePanel getInstance() {
        if (instance == null) {
            instance = new AutoCompletePanel();
        }
        return instance;
    }

    public void setSelectedNextItem() {
        // Se a lista tiver 1 ou menos itens não é necessário executar nenhuma ação.
        if (mnemonicList.getModel().getSize() <= 1) {
            return;
        }
        // Se o índice selecionado for o último então faz com que o item selecionado agora seja o primeiro.
        if (mnemonicList.getModel().getSize() == mnemonicList.getSelectedIndex() + 1) {
            mnemonicList.setSelectedIndex(0);
        } else {
            mnemonicList.setSelectedIndex(mnemonicList.getSelectedIndex() + 1);
        }

    }

    public void setSelectedPreviousItem() {
        // Se a lista tiver 1 ou menos itens não é necessário executar nenhuma ação.
        if (mnemonicList.getModel().getSize() <= 1) {
            return;
        }
        // Se o índice selecionado for o primeiro (0) então faz com que o item selecionado agora seja o último.
        if (mnemonicList.getSelectedIndex() == 0) {
            mnemonicList.setSelectedIndex(mnemonicList.getModel().getSize() - 1);
        } // Selecione o item acima do índice atual
        else {
            mnemonicList.setSelectedIndex(mnemonicList.getSelectedIndex() - 1);
        }

    }

    private void completeCommand(ComponentEvent evt) {
        // Esse é o termo que foi parcialmente digitado, ele é usado para filtrar a lista
        // de comandos a ser carregada ao apertar CTRL+SPACE
        // O filtro está na classe JListComandos

        String partialTermTyped = CommandList.getInstance().getFilter().getTermTyped();

        // Pega o item selecionado da lista
        String selectedValue = ((JList) evt.getSource()).getSelectedValue().toString();

        // Verifica se o que está digitado, já é parte do comando que vai ser inserido
        // Para evitar que apareçam caracteres dobrados como "LLOAD"
        // Então ele substitui os caracteres que foram digitados por ""
        // Limpando o comando e inserindo-o corretamente.
        if (selectedValue.startsWith(partialTermTyped)) {
            selectedValue = selectedValue.replace(partialTermTyped, "");
        }
        
        // Coloca no área de comandos o comando selecionado da lista
        // Coloca um espaço em branco após o comando

        Principal.getInstance().getMainPanel().getCodeTextArea().append(selectedValue + " ");

        // Fecha a Popup Autocomplete
        Principal.getInstance().getMainPanel().setDocumentationVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mnemonicList = CommandList.getInstance();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDocumentation = new javax.swing.JEditorPane() {
            @Override
            public void setText(String t) {
                super.setText(t);
                this.setCaretPosition(0);
            }
        };

        setInheritsPopupMenu(true);

        jScrollPane1.setInheritsPopupMenu(true);
        jScrollPane1.setOpaque(false);

        mnemonicList.setFont(new java.awt.Font("Tahoma", 1, 11));
        mnemonicList.setForeground(new java.awt.Color(51, 51, 51));
        mnemonicList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        mnemonicList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnemonicListMouseClicked(evt);
            }
        });
        mnemonicList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mnemonicListKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mnemonicListKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(mnemonicList);

        txtDocumentation.setBackground(new java.awt.Color(221, 238, 255));
        txtDocumentation.setContentType("text/html");
        txtDocumentation.setEditable(false);
        txtDocumentation.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(txtDocumentation);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mnemonicListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnemonicListKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            completeCommand(evt);
        }
    }//GEN-LAST:event_mnemonicListKeyReleased

    private void mnemonicListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnemonicListKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            Principal.getInstance().getMainPanel().setDocumentationVisible(false);
        }
    }//GEN-LAST:event_mnemonicListKeyPressed

    private void mnemonicListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnemonicListMouseClicked
        if (evt.getClickCount() == 2) {
            completeCommand(evt);
        }
    }//GEN-LAST:event_mnemonicListMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList mnemonicList;
    private javax.swing.JEditorPane txtDocumentation;
    // End of variables declaration//GEN-END:variables

    private class SelectionListener implements ListSelectionListener {
        // This method is called each time the user changes the set of selected items

        @Override
        public void valueChanged(ListSelectionEvent evt) {
            if (evt.getValueIsAdjusting() == false) {
                JList list = (JList) evt.getSource();
                String value = list.getSelectedValue().toString();
                MnemonicDetails m = MnemonicsMap.getInstance().getMnemonicsMap().get(value);
                txtDocumentation.setText(m.getDescrption());
            }
        }
    }
}
