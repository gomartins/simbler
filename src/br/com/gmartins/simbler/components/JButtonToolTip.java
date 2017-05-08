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

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JToolTip;
import javax.swing.ToolTipManager;

import br.com.gmartins.simbler.gui.colors.Colors;

/**
 * Custom JToolTip
 * @author Guilherme
 */
public class JButtonToolTip extends JButton {

    public JButtonToolTip() {
        //ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(100000);
        this.setOpaque(false);
        this.setFocusable(false);
        this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/info_small.png")));
        this.setText(null);
        this.setContentAreaFilled(false);
    }

    @Override
    public JToolTip createToolTip() {
        CustomToolTip c = new CustomToolTip();
        c.setComponent(this);
        return c;
    }

    private class CustomToolTip extends JToolTip {

        public CustomToolTip() {
            super();
            this.setBackground(new Color(235, 241, 248));
            this.setForeground(Colors.DEFAULT_LABELS);
            this.setOpaque(false);
        }
    }
}
