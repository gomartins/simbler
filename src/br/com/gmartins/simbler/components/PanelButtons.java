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
package br.com.gmartins.simbler.components;

import br.com.gmartins.simbler.Principal;
import br.com.gmartins.simbler.menuhelper.MenuActions;
import br.com.gmartins.simbler.menuhelper.MenuHandler;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * Main Buttons Component Panel.
 * @author Guilherme
 */
public class PanelButtons extends javax.swing.JPanel {

    /** Creates new form PanelButtons */
    public PanelButtons() {
        initComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image imagem = new ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/bg-buttons.jpg")).getImage();
        g.drawImage(imagem, 0, 0, this);
    }

    public void bindKeys(Principal principal) {
        /* Start Shortcut - CTRL+ENTER */
        JPanel p = (JPanel) principal.getContentPane();
        p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.CTRL_DOWN_MASK), startShortcut);
        p.getActionMap().put(startShortcut, startShortcut);
        /* Start Shortcut - SHIFT+ENTER */
        p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, KeyEvent.SHIFT_DOWN_MASK), startStepByStepShortcut);
        p.getActionMap().put(startStepByStepShortcut, startStepByStepShortcut);
        /* Stop Shortcut - CTRL+P */
        p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK), resetValuesShortcut);
        p.getActionMap().put(resetValuesShortcut, resetValuesShortcut);
        /* Start Shortcut - SHIFT+SPACE */
        p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, KeyEvent.SHIFT_DOWN_MASK), pauseShortcut);
        p.getActionMap().put(pauseShortcut, pauseShortcut);
    }
    private Action startShortcut = new AbstractAction() {

        public String actionName = "startShortcut";

        @Override
        public String toString() {
            return this.actionName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            btnStart.doClick(300);
        }
    };
    private Action startStepByStepShortcut = new AbstractAction() {

        public String actionName = "startStepByStepShortcut";

        @Override
        public String toString() {
            return this.actionName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            btnStartStepByStep.doClick(300);
        }
    };
    private Action resetValuesShortcut = new AbstractAction() {

        public String actionName = "resetValuesShortcut";

        @Override
        public String toString() {
            return this.actionName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            btnStop.doClick(300);
        }
    };
    private Action pauseShortcut = new AbstractAction() {

        public String actionName = "pauseShortcut";

        @Override
        public String toString() {
            return this.actionName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            btnPause.doClick(300);
        }
    };

    public void actionPerformed(String actionName) {
        MenuHandler handler = new MenuHandler(actionName);
        handler.executeAction();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStart = new javax.swing.JButton();
        btnStartStepByStep = new javax.swing.JButton();
        btnPause = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        btnUndo = new javax.swing.JButton();
        btnRedo = new javax.swing.JButton();

        setOpaque(false);

        btnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-play.png"))); // NOI18N
        btnStart.setToolTipText("Iniciar [CTRL+ENTER]");
        btnStart.setBorderPainted(false);
        btnStart.setContentAreaFilled(false);
        btnStart.setFocusable(false);
        btnStart.setPreferredSize(new java.awt.Dimension(60, 40));
        btnStart.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-play-over.png"))); // NOI18N
        btnStart.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-play-over.png"))); // NOI18N
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnStartStepByStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-play-step.png"))); // NOI18N
        btnStartStepByStep.setToolTipText("Passo a passo [SHIFT+ENTER]");
        btnStartStepByStep.setBorderPainted(false);
        btnStartStepByStep.setContentAreaFilled(false);
        btnStartStepByStep.setFocusable(false);
        btnStartStepByStep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStartStepByStep.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-play-step-over.png"))); // NOI18N
        btnStartStepByStep.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-play-step-over.png"))); // NOI18N
        btnStartStepByStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartStepByStepActionPerformed(evt);
            }
        });

        btnPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-pause.png"))); // NOI18N
        btnPause.setToolTipText("Pausar  [SHIFT+ESPAÇO]");
        btnPause.setBorderPainted(false);
        btnPause.setContentAreaFilled(false);
        btnPause.setFocusable(false);
        btnPause.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-pause-over.png"))); // NOI18N
        btnPause.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-pause-over.png"))); // NOI18N
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-stop.png"))); // NOI18N
        btnStop.setToolTipText("Parar [CTRL+P]");
        btnStop.setBorderPainted(false);
        btnStop.setContentAreaFilled(false);
        btnStop.setFocusable(false);
        btnStop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStop.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-stop-over.png"))); // NOI18N
        btnStop.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-stop-over.png"))); // NOI18N
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        btnUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-undo.png"))); // NOI18N
        btnUndo.setToolTipText("Desfazer [CTRL+Z]");
        btnUndo.setBorderPainted(false);
        btnUndo.setContentAreaFilled(false);
        btnUndo.setFocusable(false);
        btnUndo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUndo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-undo-over.png"))); // NOI18N
        btnUndo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-undo-over.png"))); // NOI18N
        btnUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUndoActionPerformed(evt);
            }
        });

        btnRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-redo.png"))); // NOI18N
        btnRedo.setToolTipText("Refazer [CTRL+Y]");
        btnRedo.setBorderPainted(false);
        btnRedo.setContentAreaFilled(false);
        btnRedo.setFocusable(false);
        btnRedo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRedo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-redo-over.png"))); // NOI18N
        btnRedo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/btn-redo-over.png"))); // NOI18N
        btnRedo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnStartStepByStep, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnRedo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRedo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUndo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStop, 0, 0, Short.MAX_VALUE)
                    .addComponent(btnPause, 0, 0, Short.MAX_VALUE)
                    .addComponent(btnStartStepByStep, 0, 0, Short.MAX_VALUE)
                    .addComponent(btnStart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        this.actionPerformed(MenuActions.ACT_START);
}//GEN-LAST:event_btnStartActionPerformed

    private void btnStartStepByStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartStepByStepActionPerformed
        this.actionPerformed(MenuActions.ACT_STEP_BY_STEP);
}//GEN-LAST:event_btnStartStepByStepActionPerformed

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        this.actionPerformed(MenuActions.ACT_PAUSE);
}//GEN-LAST:event_btnPauseActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        this.actionPerformed(MenuActions.ACT_STOP);

}//GEN-LAST:event_btnStopActionPerformed

    private void btnUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUndoActionPerformed
        this.actionPerformed(MenuActions.ACT_UNDO);

    }//GEN-LAST:event_btnUndoActionPerformed

    private void btnRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedoActionPerformed
        this.actionPerformed(MenuActions.ACT_REDO);

    }//GEN-LAST:event_btnRedoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnRedo;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStartStepByStep;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton btnUndo;
    // End of variables declaration//GEN-END:variables
}
