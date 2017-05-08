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

import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;

import br.com.gmartins.simbler.components.CodeTextArea;

/**
 *
 * @author Guilherme
 */
public class LineValue {

    private int caretPositionLineStart;
    private int caretPositionLineEnd;

    public int getCaretPositionLineEnd() {
        return caretPositionLineEnd - 1;
    }

    public int getCaretPositionLineStart() {
        return caretPositionLineStart;
    }

    /**
     * Return an int containing the wrapped line index at the given position
     * @param component JTextPane
     * @param int pos
     * @return int
     */
    public int getCurrentLineNumber(CodeTextArea component, int pos) {
        int posLine;
        int y = 0;
        try {
            Rectangle caretCoords = component.modelToView(pos);
            y = (int) caretCoords.getY();
        } catch (BadLocationException ex) {
            Logger.getLogger(LineValue.class.getName()).log(Level.SEVERE, null, ex);
        }

        int lineHeight = component.getFontMetrics(component.getFont()).getHeight();
        posLine = (y / lineHeight);
        return posLine;
    }

    public String getLineText(int lineNumber, CodeTextArea component, boolean withComments) {
        String nLine = String.valueOf(lineNumber);
        return getLineText(nLine, component, withComments);
    }

    public String getLineText(int lineNumber, CodeTextArea component) {
        String nLine = String.valueOf(lineNumber);
        return getLineText(nLine, component, true);
    }

    private String getLineText(String nLinha, CodeTextArea component, boolean withComments) {
        int nLinhaInt = Integer.parseInt(nLinha);
        Document doc = component.getDocument();
        Element rootElement = doc.getDefaultRootElement();
        Element rowElement = rootElement.getElement(nLinhaInt);
        this.caretPositionLineStart = rowElement.getStartOffset();
        this.caretPositionLineEnd = rowElement.getEndOffset();
        String row = null;
        try {
            row = doc.getText(getCaretPositionLineStart(), getCaretPositionLineEnd() - getCaretPositionLineStart());
        } catch (BadLocationException ex) {
            Logger.getLogger(LineValue.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (withComments == false) {
            // Procura por comentários na linha
            Pattern p = Pattern.compile("(\\s*;\\s*.*)");
            Matcher m = p.matcher(row);
            // Se encontrar, define a posição final da linha com a posição inicial do comentário (+1 para corrigir o -1 do método.)
            while (m.find()) {
                this.caretPositionLineEnd = m.start() + 1;
                try {
                    // Define o novo valor de row
                    row = doc.getText(getCaretPositionLineStart(), getCaretPositionLineEnd());
                } catch (BadLocationException ex) {
                    Logger.getLogger(LineValue.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
        return row;
    }
}
