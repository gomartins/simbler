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

import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

/**
 * Class used to assist with getting texts on JTextAreas with MouseEvents.
 * @author Guilherme
 */
public class WordUtilities {

    /**
     * Returns the current pointed word on the JTextArea.
     * @param e 
     * @return 
     */
    public static String getPointedWord(MouseEvent e) {
        JTextArea text = (JTextArea) e.getSource();
        try {
            int pos = getPointedCharacterOffset(e);
            int start = Utilities.getWordStart(text, pos);
            int end = Utilities.getWordEnd(text, pos);
            int lenght = end - start;
            String word = text.getText(start, lenght);
            return word;
        } catch (BadLocationException ex) {
            Logger.getLogger(WordUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Returns the character position where the mouse is pointing.
     * @param e
     * @return 
     */
    private static int getPointedCharacterOffset(MouseEvent e) {
        JTextArea text = (JTextArea) e.getSource();
        int pos = text.getUI().viewToModel(text, e.getPoint());
        return pos;
    }

    /**
     * Returns the line position where the mouse is pointing.
     * @param e
     * @return 
     */
    public static int getPointedLinePosition(MouseEvent e) {
        try {
            JTextArea text = (JTextArea) e.getSource();
            return text.getLineOfOffset(getPointedCharacterOffset(e));
        } catch (BadLocationException ex) {
            Logger.getLogger(WordUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
