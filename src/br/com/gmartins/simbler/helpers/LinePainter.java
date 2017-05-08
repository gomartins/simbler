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
package br.com.gmartins.simbler.helpers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

/*
 *  Track the movement of the Caret by painting a background line at the
 *  current caret position.
 */
public class LinePainter implements Highlighter.HighlightPainter, CaretListener, MouseListener, MouseMotionListener {

    private JTextComponent component;
    private Color color;
    private Rectangle lastView;

    /*
     *  The line color will be calculated automatically by attempting
     *  to make the current selection lighter by a factor of 1.2.
     *
     *  @param component  text component that requires background line painting
     */
    public LinePainter(JTextComponent component) {
        this(component, null);
        setLighter(component.getSelectionColor());
    }

    /*
     *  Manually control the line color
     *
     *  @param component  text component that requires background line painting
     *  @param color      the color of the background line
     */
    private LinePainter(JTextComponent component, Color color) {
        this.component = component;
        setColor(color);

        //  Add listeners so we know when to change highlighting

        component.addCaretListener(this);
        component.addMouseListener(this);
        component.addMouseMotionListener(this);

        //  Turn highlighting on by adding a dummy highlight

        try {
            component.getHighlighter().addHighlight(0, 0, this);
        } catch (BadLocationException ble) {
        }
    }

    /*
     *	You can reset the line color at any time
     *
     *  @param color  the color of the background line
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /*
     *  Calculate the line color by making the selection color lighter
     *
     *  @return the color of the background line
     */
    public final void setLighter(Color color) {
        int red = Math.min(255, (int) (color.getRed() * 1.2));
        int green = Math.min(255, (int) (color.getGreen() * 1.2));
        int blue = Math.min(255, (int) (color.getBlue() * 1.2));
        setColor(new Color(red, green, blue));
    }

    //  Paint the background highlight
    public void paint(Graphics g, int p0, int p1, Shape bounds, JTextComponent c) {
        try {
            Rectangle r = c.modelToView(c.getCaretPosition());
            g.setColor(color);
            g.fillRect(42, r.y, c.getWidth(), r.height);

            if (lastView == null) {
                lastView = r;
            }
        } catch (BadLocationException ble) {
            System.out.println(ble);
        }
    }

    /*
     *   Caret position has changed, remove the highlight
     */
    private void resetHighlight() {
        //  Use invokeLater to make sure updates to the Document are completed,
        //  otherwise Undo processing causes the modelToView method to loop.

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    int offset = component.getCaretPosition();

                    Rectangle currentView = component.modelToView(offset);

                    //  Remove the highlighting from the previously highlighted line

                    if (lastView.y != currentView.y) {
                        component.repaint(0, lastView.y, component.getWidth(), lastView.height);
                        component.repaint(); // Repaint adicionado pois estava causando problema nos n�meros da Linha do JTextArea
                        lastView = currentView;

                    }
                } catch (BadLocationException ble) {
                }
            }
        });
    }

    //  Implement CaretListener
    public void caretUpdate(CaretEvent e) {
        resetHighlight();
    }

    //  Implement MouseListener
    public void mousePressed(MouseEvent e) {
        resetHighlight();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    //  Implement MouseMotionListener
    public void mouseDragged(MouseEvent e) {
        resetHighlight();
    }

    public void mouseMoved(MouseEvent e) {
    }
}
