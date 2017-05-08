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

import br.com.gmartins.simbler.gui.colors.Colors;
import br.com.gmartins.simbler.menuhelper.MenuHandler;
import com.jidesoft.swing.JideTabbedPane;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * Custom JideTabbedPane component.
 * It has some particular methods and a different close action.
 * @author Guilherme
 */
public class TabbedPane extends JideTabbedPane {

    public TabbedPane() {
        this.setCloseAction(closeAction);
        this.setBackground(Colors.TABBED_PANE_BACKGROUND);
        this.setAutoFocusOnTabHideClose(false);
        this.setColorTheme(4);
        this.setShowCloseButtonOnSelectedTab(true);
        this.setShowCloseButtonOnTab(true);
    }

    /**
     * Set selected the last tab of the TabbedPane component.
     */
    public void setLastTabSelected() {
        if (this.getTabCount() <= 1) {
            return;
        }
        this.setSelectedIndex(this.getTabCount() - 1);
    }
    private Action closeAction = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            MenuHandler m = new MenuHandler(e);
            m.eventCloseTab();
        }
    };
}
