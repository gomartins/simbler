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

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import br.com.gmartins.simbler.components.MainPanel;

/**
 *
 * @author Guilherme
 */
public abstract class DataTypeComponent extends JPanel {

    private MainPanel panel;
    private DataType dataType;
    private Icon icon;
    private JMenuItem binary;
    private JMenuItem decimal;
    private JMenuItem hexadecimal;
    private JPopupMenu popupMenu;

    public DataTypeComponent(MainPanel panel) {
        initComponents();
        this.panel = panel;
    }

    public DataTypeComponent() {
        this(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image imagem = new ImageIcon(getClass().getResource(this.getBackgroundImage())).getImage();
        g.drawImage(imagem, 0, 0, this);
    }

    public abstract String getBackgroundImage();

    private void initComponents() {
        icon = new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/datatype-tick.png"));
        binary = new JMenuItem(DataType.BINARY.getName());
        decimal = new JMenuItem(DataType.DECIMAL.getName());
        hexadecimal = new JMenuItem(DataType.HEXADECIMAL.getName(), icon);
        popupMenu = new JPopupMenu();
        popupMenu.add(binary);
        popupMenu.add(decimal);
        popupMenu.add(hexadecimal);
    }

    public Icon getSelectedMenuItemIcon() {
        return icon;
    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public void setMenuItemAction(ActionListener action) {
        for (int i = 0; i < getPopupMenu().getComponentCount(); i++) {
            if (getPopupMenu().getComponent(i) instanceof JMenuItem) {
                ((JMenuItem) getPopupMenu().getComponent(i)).addActionListener(action);
            }
        }
    }

    public void onClickEvent(ActionEvent e) {
        this.resetMenuItemIcons(); // Remove todos os ícones do Menu
        ((JMenuItem) e.getSource()).setIcon(this.getSelectedMenuItemIcon());
        if (e.getActionCommand().equals(DataType.BINARY.getName())) {
            this.dataType = DataType.BINARY;
        } else if (e.getActionCommand().equals(DataType.DECIMAL.getName())) {
            this.dataType = DataType.DECIMAL;
        } else if (e.getActionCommand().equals(DataType.HEXADECIMAL.getName())) {
            this.dataType = DataType.HEXADECIMAL;
        }
    }

    /**
     * Get the linked main panel.
     * @return the main panel
     */
    public MainPanel getPanel() {
        return panel;
    }

    /**
     * Set a data type system. (Binary, Decimal or Hexadecimal)
     * @see DataType
     * @param type 
     */
    public void setDataType(DataType type) {
        JMenuItem selectedMenu = null;
        switch (type) {
            case BINARY:
                selectedMenu = binary;
                break;
            case DECIMAL:
                selectedMenu = decimal;
                break;
            case HEXADECIMAL:
                selectedMenu = hexadecimal;
                break;
        }
        ActionEvent e = new ActionEvent(selectedMenu, -1, type.getName());
        onClickEvent(e);
    }

    /**
     * Return the current data type system.
     * @return the data type
     */
    public DataType getDataType() {
        return dataType;
    }

    private void resetMenuItemIcons() {
        for (int i = 0; i < getPopupMenu().getComponentCount(); i++) {
            if (getPopupMenu().getComponent(i) instanceof JMenuItem) {
                ((JMenuItem) getPopupMenu().getComponent(i)).setIcon(null);
            }
        }
    }
}
