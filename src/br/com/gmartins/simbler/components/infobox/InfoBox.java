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
package br.com.gmartins.simbler.components.infobox;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.sun.awt.AWTUtilities;

/**
 * Cointaner used to show the "InfoBox" dialogs.
 * "InfoBox" dialogs are used to show a custom popup on window with some information.
 * @author Guilherme
 */
public class InfoBox extends javax.swing.JDialog {


    /**
     * Create a new Dialog.
     * @param parent
     * @param modal 
     */
    public InfoBox(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);
        textArea.setEditable(false);
        JPanel p = (JPanel) this.getContentPane();
        p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "btnClose");
        p.getActionMap().put("btnClose", closeAction);
    }
    private Action closeAction = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    };
    private Point mMouseClickPoint;
    private StringBuilder generatedString = new StringBuilder();

    private void moveWindowTo(Point point) {
        int musDiffX = point.x - mMouseClickPoint.x;
        int musDiffY = point.y - mMouseClickPoint.y;
        setLocation(getLocation().x + musDiffX, getLocation().y + musDiffY);
        this.setLocation(getLocation());
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        lblTitle.setText(title);
    }

    @Override
    public String getTitle() {
        return lblTitle.getText();
    }

    /**
     * Set the title of the dialog.
     * @param text title
     */
    public void setTitleMessage(String text) {
        generatedString.append("<html> <body> ");
        generatedString.append("<p style=\"font:verdana; color:#06C; font-size: 9px;\"><strong>");
        generatedString.append(text);
        generatedString.append("</strong></p>");
    }

    /**
     * Set the first line message of the dialog.
     * @param text message
     */
    public void setFirstMessage(String text) {
        if (generatedString.toString().isEmpty()) {
            throw new IllegalArgumentException("É necessário definir o título antes da mensagem! Veja setTitleMessage.");
        }
        generatedString.append("<p style=\"font:verdana; font-weight:bold; font-size: 9px;\">");
        generatedString.append(text);
        generatedString.append("</p>");

    }

    /**
     * Set the second line message of the dialog.
     * @param text message
     */
    public void setSecondMessage(String text) {
        if (generatedString.toString().isEmpty()) {
            throw new IllegalArgumentException("É necessário definir o título antes da mensagem! Veja setTitleMessage.");
        }
        generatedString.append("<p style=\"font:verdana;  font-weight:bold; font-size: 9px;\">");
        generatedString.append(text);
        generatedString.append("</p>");

    }

    /**
     * Set the third line message of the dialog.
     * @param text message
     */
    public void setThirdMessage(String text) {
        if (generatedString.toString().isEmpty()) {
            throw new IllegalArgumentException("É necessário definir o título antes da mensagem! Veja setTittleMessage.");
        }
        generatedString.append("<p style=\"font:Tahoma; color:red; font-weight:bold; font-size: 9px;\">");
        generatedString.append(text);
        generatedString.append("</p>");
        generatedString.append("</html> </body> ");
        textArea.setText(generatedString.toString());
    }

    
    /**
     * Return the JTextArea message
     * @return The written message
     */
    public String getMessage() {
        return textArea.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXRootPane1 = new org.jdesktop.swingx.JXRootPane();
        panelContainer = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagem = new ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/infobox-bg.png")).getImage();
                g.drawImage(imagem, 0, 0, this);

            }

        };
        lblIcon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextPane();
        btnClose = new javax.swing.JButton();
        panelTitle = new javax.swing.JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image imagem = new ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/infobox-title-bg.png")).getImage();
                g.drawImage(imagem, 3, 0, this);

            }

        };
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 240));
        setResizable(false);
        setUndecorated(true);

        panelContainer.setOpaque(false);
        panelContainer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelContainerMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelContainerMousePressed(evt);
            }
        });
        panelContainer.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelContainerMouseDragged(evt);
            }
        });

        lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/infobox-icon-info.png"))); // NOI18N

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        textArea.setBorder(null);
        textArea.setContentType("text/html");
        textArea.setFont(new java.awt.Font("Tahoma", 0, 10));
        textArea.setText("");
        textArea.setMargin(new java.awt.Insets(2, 2, 2, 2));
        textArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                textAreaMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(textArea);

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/infobox-button-close.png"))); // NOI18N
        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.setFocusPainted(false);
        btnClose.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/infobox-button-close-over.png"))); // NOI18N
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCloseMouseEntered(evt);
            }
        });
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        panelTitle.setOpaque(false);
        panelTitle.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setPreferredSize(new java.awt.Dimension(320, 30));
        panelTitle.add(lblTitle);

        javax.swing.GroupLayout panelContainerLayout = new javax.swing.GroupLayout(panelContainer);
        panelContainer.setLayout(panelContainerLayout);
        panelContainerLayout.setHorizontalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(panelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
        );
        panelContainerLayout.setVerticalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContainerLayout.createSequentialGroup()
                .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelContainerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(lblIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                    .addGroup(panelContainerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXRootPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(395, Short.MAX_VALUE))
            .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXRootPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
            .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelContainerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelContainerMousePressed
        mMouseClickPoint = evt.getPoint();
    }//GEN-LAST:event_panelContainerMousePressed
    private void panelContainerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelContainerMouseDragged
        moveWindowTo(evt.getPoint());
    }//GEN-LAST:event_panelContainerMouseDragged

    private void textAreaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textAreaMouseEntered
        setCursor(new Cursor(Cursor.TEXT_CURSOR));
    }//GEN-LAST:event_textAreaMouseEntered

    private void panelContainerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelContainerMouseEntered
        setCursor(new Cursor(Cursor.MOVE_CURSOR));
    }//GEN-LAST:event_panelContainerMouseEntered

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                InfoBox dialog = new InfoBox(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXRootPane jXRootPane1;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel panelContainer;
    private javax.swing.JPanel panelTitle;
    private javax.swing.JTextPane textArea;
    // End of variables declaration//GEN-END:variables
}
