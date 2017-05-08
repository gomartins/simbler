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
package br.com.gmartins.simbler.components.tooltip;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;

/**
 *
 * @author Guilherme
 */
public class TextHighlighter {

    private Color color;
    private final JTextArea textComponent;
    private UnderlineHighlighter highlighter;

    public TextHighlighter(JTextArea text, Color color) {
        this.color = color;
        this.textComponent = text;
        highlighter = new UnderlineHighlighter(this.color);
    }

    public JTextArea getTextComponent() {
        return textComponent;
    }

    public void setHighlighted(String pattern) {
        // First remove all old highlights
        // removeHighlights(this.getTextComponent());
        findAndHighlight(pattern, highlighter);
    }

    public void setHighlightedLine(int pos) {
        try {
            Highlighter h = this.getTextComponent().getHighlighter();
            int start = this.getTextComponent().getLineStartOffset(pos);
            int end = this.getTextComponent().getLineEndOffset(pos);
            h.addHighlight(start, end, highlighter);
        } catch (BadLocationException ex) {
            Logger.getLogger(TextHighlighter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void findAndHighlight(String pattern, Highlighter.HighlightPainter highlighter) {
        try {
            Highlighter hilite = this.getTextComponent().getHighlighter();
            Document doc = this.getTextComponent().getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos = 0;

            // Search for pattern
            while ((pos = text.indexOf(pattern, pos)) >= 0) {
                // Create highlighter using private painter and apply around pattern
                hilite.addHighlight(pos, pos + pattern.length(), highlighter);
                pos += pattern.length();
            }
        } catch (BadLocationException e) {
        }
    }

    /**
     * Remove the defined highlights
     */
    public void removeHighlights() {
        Highlighter hilite = this.getTextComponent().getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();
        for (int i = 0; i < hilites.length; i++) {
            if (hilites[i].getPainter() instanceof UnderlineHighlighter) {
                hilite.removeHighlight(hilites[i]);
            }
        }
    }
}