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
package br.com.gmartins.simbler;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.KeyStroke;

import br.com.gmartins.simbler.components.CodeTextArea;
import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.components.MenuBar;
import br.com.gmartins.simbler.components.TabbedPane;
import br.com.gmartins.simbler.gui.colors.Colors;
import br.com.gmartins.simbler.helpers.InitialSettings;
import br.com.gmartins.simbler.menuhelper.MenuActions;
import br.com.gmartins.simbler.menuhelper.MenuHandler;

/**
 * The main face of the application.
 * @author Guilherme Martins
 */
public class Principal extends javax.swing.JFrame {

    private static Principal instance;

    public Principal() {
        InitialSettings init = new InitialSettings(this);
        initComponents();
        init.setBackgroundColor(Colors.DEFAULT_BACKGROUND);
        init.centralizeFrame();
        setJMenuBar(new MenuBar().getMenu());
    }

    private void bindKeys() {
        JPanel panel = (JPanel) this.getContentPane();
        panel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, KeyEvent.CTRL_DOWN_MASK), "Auto-complete");
        panel.getActionMap().put("Auto-complete", autoCompleteAction);
    }

    public static Principal getInstance() {
        if (instance == null) {
            instance = new Principal();
        }
        return instance;
    }

    public TabbedPane getTabbedPane() {
        return this.tabbedPane;
    }
    private Action autoCompleteAction = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            getMainPanel().setDocumentationVisible(true);
        }
    };

    /**
     * This method returns the Coding text area (JTextArea).
     * @return JTextArea
     */
    public CodeTextArea getTextArea() {
        return this.getMainPanel().getCodeTextArea();
    }

    /**
     * This method returns the current selected tabbedPane on the main frame.
     * @return The selected tabbed pane. 
     */
    public MainPanel getMainPanel() {
        return (MainPanel) tabbedPane.getSelectedComponent();
    }

    /**
     * Returns the JProgressBar on the bottom bar of the main frame.
     * @return JProgressBar
     */
    public JProgressBar getProgressBar() {
        return progressBar;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusBar = new org.jdesktop.swingx.JXStatusBar();
        progressBar = new javax.swing.JProgressBar();
        buttonPanel = new br.com.gmartins.simbler.components.PanelButtons();
        tabbedPane = new br.com.gmartins.simbler.components.TabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Simbler v1.12");
        setMinimumSize(new java.awt.Dimension(500, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        statusBar.setMinimumSize(new java.awt.Dimension(0, 20));
        statusBar.setPreferredSize(new java.awt.Dimension(500, 20));

        progressBar.setVisible(false);

        statusBar.setResizeHandleEnabled(false);

        javax.swing.GroupLayout statusBarLayout = new javax.swing.GroupLayout(statusBar);
        statusBar.setLayout(statusBarLayout);
        statusBarLayout.setHorizontalGroup(
            statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
        );
        statusBarLayout.setVerticalGroup(
            statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Opens a new editor
        new MenuHandler(MenuActions.ACT_NEW).executeAction();
        this.bindKeys();
        this.buttonPanel.bindKeys(Principal.this);
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        MenuHandler menuHandler = new MenuHandler(MenuActions.ACT_EXIT);
        menuHandler.executeAction();
    }//GEN-LAST:event_formWindowClosing

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Principal.getInstance().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.gmartins.simbler.components.PanelButtons buttonPanel;
    private javax.swing.JProgressBar progressBar;
    private org.jdesktop.swingx.JXStatusBar statusBar;
    private br.com.gmartins.simbler.components.TabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
