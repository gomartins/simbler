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
package br.com.gmartins.simbler.menuhelper;

import br.com.gmartins.simbler.components.MenuBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.text.JTextComponent;

public class RightClickMenu extends MouseAdapter {

    private JTextComponent component;
    private JPopupMenu mpopup;

    public void addMenuTo(JTextComponent theComponent) {
        component = theComponent;
        component.addMouseListener(this);
        mpopup = new JPopupMenu();
        MenuBar menuBar = new MenuBar();
        addMenuItem(menuBar.getItemMenu(MenuActions.ACT_UNDO));
        addMenuItem(menuBar.getItemMenu(MenuActions.ACT_REDO));
        addMenuSeparator();
        addMenuItem(menuBar.getItemMenu(MenuActions.ACT_CUT));
        addMenuItem(menuBar.getItemMenu(MenuActions.ACT_COPY));
        addMenuItem(menuBar.getItemMenu(MenuActions.ACT_PASTE));
        addMenuSeparator();
        addMenuItem(menuBar.getItemMenu(MenuActions.ACT_START));
        addMenuItem(menuBar.getItemMenu(MenuActions.ACT_STEP_BY_STEP));
        addMenuItem(menuBar.getItemMenu(MenuActions.ACT_PAUSE));
        addMenuItem(menuBar.getItemMenu(MenuActions.ACT_STOP));
        addMenuSeparator();
        addMenuItem(menuBar.getItemMenu(MenuActions.ACT_GOTO));
        addMenuItem(menuBar.getItemMenu(MenuActions.ACT_SELECT_ALL));
        addMenuItem(menuBar.getItemMenu(MenuActions.ACT_INSERT_LINE));
        addMenuItem(menuBar.getItemMenu(MenuActions.ACT_REMOVE_LINE));
    }

    private void addMenuItem(JMenuItem item) {
        // item.addActionListener(this);
        mpopup.add(item);
    }

    private void addMenuSeparator() {
        mpopup.add(new JSeparator());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        maybePopup(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        maybePopup(e);
    }

    private void maybePopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            mpopup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
