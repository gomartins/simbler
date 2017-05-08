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
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Element;

import br.com.gmartins.simbler.Principal;
import br.com.gmartins.simbler.compiler.analyzers.LexAnalyzerWorker;
import br.com.gmartins.simbler.components.tooltip.WordRecognizer;
import br.com.gmartins.simbler.components.tooltip.WordUtilities;
import br.com.gmartins.simbler.helpers.LinePainter;

/**
 *
 * @author Guilherme
 */
public class CodeTextArea extends javax.swing.JTextArea implements KeyListener {

    private int currentLineNumber;

    public CodeTextArea() {
        initialSettings();
    }

    private void initialSettings() {
        setOpaque(false);
        setColumns(1);
        setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        setRows(1);
        setMargin(new java.awt.Insets(0, 5, 2, 2));
        this.setSelectionColor(Color.BLACK);

        // Adiciona o LinePainter para monitorar a posição do cursor de texto e pintar as linhas
        LinePainter linePainter = new LinePainter(this);
        // Define a cor do LinePainter
        linePainter.setColor(new Color(156, 215, 237));




        // Troca o tipo de Document para que aceite apenas caracteres maiúsculos
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new UppercaseDocumentFilter());

        // Define um texto vazio como ToolTipText para ativar a visualização desses elementos
        setToolTipText("");

        // Adiciona o KeyListener para monitorar a digitação dos códigos
        this.addKeyListener(this);
    }


    @Override
    public String getToolTipText(MouseEvent event) {
        if (Principal.getInstance().getMainPanel().getCommandList() != null && Principal.getInstance().getMainPanel().getCommandList().isEmpty() == false) {
            String word = WordUtilities.getPointedWord(event);
            int pos = WordUtilities.getPointedLinePosition(event);
            WordRecognizer wordRecognizer = new WordRecognizer(word, pos);
            return wordRecognizer.getKnownData();
        }
        return null;
    }

    @Override
    public Insets getInsets() {
        return getInsets(new Insets(0, 0, 0, 0));
    }

    /**
     * This modifies the insets, by adding space for the line number on the
     * left. Should be modified to add space on the right, depending upon
     * Locale.
     */
    @Override
    public Insets getInsets(Insets insets) {
        insets = super.getInsets(insets);
        //insets.left += lineNumberWidth();
        insets.left = 45;
        return insets;
    }

    public int getCurrentLineNumber() {

        try {
            this.currentLineNumber = this.getLineOfOffset(this.getCaretPosition());
            return currentLineNumber;
        } catch (BadLocationException ex) {
            try {
                this.currentLineNumber = this.getLineOfOffset(this.getCaretPosition() - 1);
                return currentLineNumber;
            } catch (BadLocationException ex1) {
                Logger.getLogger(CodeTextArea.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return 0;
    }

    public void replaceTextOfLine(int line, String newText, int start, int end) {
        Document doc = this.getDocument();
        Element rootElement = doc.getDefaultRootElement();
        Element rowElement = rootElement.getElement(line);
        int characterCount = rowElement.getStartOffset();
        int caretStart = characterCount + start;
        int caretEnd = characterCount + end;
        this.replaceRange(newText, caretStart, caretEnd);
    }

    @Override
    public void paint(Graphics g) {
        Insets insets = getInsets();

        Rectangle clip = g.getClipBounds();

        g.setColor(getBackground()); // see note in constructor about this...
        g.fillRect(clip.x, clip.y, clip.width, clip.height);


        // do the line numbers need redrawn?
        if (clip.x < insets.left) {
            FontMetrics fm = g.getFontMetrics();
            int fontHeight = fm.getHeight();

            // starting location at the "top" of the page...
            // y is the starting baseline for the font...
            // should "font leading" be applied?
            int y = fm.getAscent() + insets.top;

            //
            // now determine if it is the "top" of the page...or somewhere else
            //
            int startingLineNumber = ((clip.y + insets.top) / fontHeight) + 1;

            //
            // use any one of the following if's:
            //
            //			if (startingLineNumber != 1)
            if (y < clip.y) {
                //
                // not within the clip rectangle...move it...
                // determine how many fontHeight's there are between
                // y and clip.y...then add that many fontHeights
                //

                y = startingLineNumber * fontHeight - (fontHeight - fm.getAscent());
            }

            //
            // options:
            // . write the number rows in the document (current)
            // . write the number of existing lines in the document (to do)
            //   see getLineCount()
            //
            // determine which the "drawing" should end...
            // add fontHeight: make sure...part of the line number is drawn
            //
            // could also do this by determining what the last line
            // number to draw.
            // then the "while" loop whould change accordingly.
            //
            // int yend = y + clip.height + fontHeight;


            // Alteração: yend = getLineCount() * fontHeight para que a contagem das linhas sejam feitas de acordo que forem usadas, comecando da 0
            int yend = getLineCount() * fontHeight;
            // base x position of the line number


            // g.setColor(getForeground());
            //
            // loop until out of the "visible" region...
            //
            g.setColor(new Color(130, 130, 130));
            int length = ("" + Math.max(getRows(), getLineCount() + 1)).length();
            while (y < yend) {
                //
                // options:
                // . left justify the line numbers (current)
                // . right justify the line number (to do)
                //

                // right justify
                // Original - Foi adicionado -1 ao startingLineNumber para comr
                // String label = padLabel(startingLineNumber, length, true);
                String label = padLabel(startingLineNumber - 1, length, true);
                g.drawString(label, insets.left - fm.stringWidth(label), y);


                y += fontHeight;
                startingLineNumber++;
            }
        } // draw line numbers?

        g.setColor(new Color(96, 195, 232));
        g.drawLine(41, 0, 41, this.getSize().height);
        super.paint(g);

    } // paintComponent

    /**
     * Create the string for the line number.
     * NOTE: The <tt>length</tt> param does not include the
     * <em>optional</em> space added after the line number.
     *
     * @param lineNumber to stringize
     * @param length     the length desired of the string
     * @param length     the length desired of the string
     *
     * @return the line number for drawing
     */
    private String padLabel(int lineNumber, int length, boolean addSpace) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(lineNumber);
        for (int count = (length - buffer.length()); count > 0; count--) {
            buffer.insert(0, ' ');
        }
        if (addSpace) {
            buffer.append(' ');
        }
        return buffer.toString();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        idleKeyboardAnalyzer();
    }
    private TextEventWorker worker = new TextEventWorker();

    /**
     * Toda tecla "SOLTA" ou "RELEASED" ativa esse método.
     * Esse método verifica se um pedido de verificação já está pendente, se sim, quer dizer que esse método foi invocado
     * antes da thread acordar (foi definido 1000ms de sleep), então faz que a verificação cancele, iniciando uma nova Thread
     * e pedindo para que ela durma por mais 1000ms, caso aconteça um novo pressionamento de tecla antes desse intervalo terminar
     * a Thread é cancelada novamente. Esse ciclo se mantém até o usuário parar de digitar por mais de um segundo.
     * Após o usuário parar de digitar por mais de 01 segundo, é feita a verificação dos comandos montando a estrutura de instruções,
     * podendo fazer a geração dos JToolTip e outras informações corretamente.
     */
    public void idleKeyboardAnalyzer() {
        // Só faz a verificação se for menor que 300 linhas. Evita problemas de performance.
        // Se a Thread tiver comecado ou já tiver terminado, entra
        // Essa verificação é crucial para parar a Thread se ela já tiver começado, evita que o programa faça verificações
        // desnecessárias de código.
        if (worker.getState() == SwingWorker.StateValue.STARTED || worker.getState() == SwingWorker.StateValue.DONE) {

            // Se o status for diferente de DONE, tenta pará-la.
            if (worker.getState() != SwingWorker.StateValue.DONE) {
                worker.cancel(true);
            }
            // Inicia uma nova thread
            worker = new TextEventWorker();
        }
        // E executa
        worker.execute();
    }

    private void updateTitleOnCodeChange() {
        JTabbedPane tabbedPane = Principal.getInstance().getTabbedPane();
        int selectedIndex = tabbedPane.getSelectedIndex();
        String title = tabbedPane.getTitleAt(selectedIndex);
        if (Principal.getInstance().getMainPanel().isCodeChanged()) {
            if (title.contains("*") == false) {
                title += " *";
                tabbedPane.setTitleAt(selectedIndex, title);
            }
        } else {
            title = title.replace("*", "").trim();
            tabbedPane.setTitleAt(selectedIndex, title);
        }
    }

    private class TextEventWorker extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            try {
                // Aguarda um segundo antes de começar a analisar o código
                // Se outro evento de pressionamento de tecla ocorrer enquanto essa Thread aguarda, essa Thread será cancelada e uma
                // nova se iniciará, fazendo com que o código seja analisado apenas quando o usuário parar de digitar por mais de 1000s.
                Thread.sleep(1000);

                updateTitleOnCodeChange();

                LexAnalyzerWorker worker = new LexAnalyzerWorker(Principal.getInstance().getMainPanel(), false);
                worker.setProgressVisible(true, getLineCount());
                worker.setSilentMode(true);
                worker.execute();

                return null;
            } catch (InterruptedException ex) {
                // Ok. Exceção esperada... Ignorando
            }
            return null;
        }
    }

    private class UppercaseDocumentFilter extends DocumentFilter {

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            fb.insertString(offset, text.toUpperCase(), attr);
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            fb.replace(offset, length, text.toUpperCase(), attrs);
        }
    }
}
