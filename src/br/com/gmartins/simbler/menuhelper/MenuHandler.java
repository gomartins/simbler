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

import br.com.gmartins.simbler.Principal;
import br.com.gmartins.simbler.compiler.analyzers.LexAnalyzerWorker;
import br.com.gmartins.simbler.components.CodeTextArea;
import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.components.infobox.About;
import br.com.gmartins.simbler.properties.Text;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Guilherme
 */
public class MenuHandler {

    private String actionCommandName;
    private FileEventsHelper fileEventsHelper = new FileEventsHelper();

    public MenuHandler(String evtName) {
        this.actionCommandName = evtName;
    }

    public MenuHandler(ActionEvent evt) {
        this(evt.getActionCommand());
    }

    private void eventPaste() {
        getTextArea().paste();
    }

    private CodeTextArea getTextArea() {
        if (getMainPanel() == null) {
            return null;
        } else {
            return getMainPanel().getCodeTextArea();
        }
    }

    private MainPanel getMainPanel() {
        if (Principal.getInstance().getMainPanel() == null) {
            JOptionPane.showMessageDialog(null, Text.get("general.before_execute"));
            return null;
        }
        return Principal.getInstance().getMainPanel();
    }

    private void eventCut() {
        if (getMainPanel() != null) {
            getTextArea().cut();
        }
    }

    private void eventCopy() {
        if (getMainPanel() != null) {
            getTextArea().copy();
        }
    }

    private void eventDelete() {
        if (getMainPanel() != null) {
            getTextArea().replaceSelection("");
        }
    }

    private void eventUndo() {
        if (getMainPanel() != null) {
            if (Principal.getInstance().getMainPanel().getUndoManager().canUndo()) {
                Principal.getInstance().getMainPanel().getUndoManager().undo();
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    private void eventRedo() {
        if (getMainPanel() != null) {
            if (Principal.getInstance().getMainPanel().getUndoManager().canRedo()) {
                Principal.getInstance().getMainPanel().getUndoManager().redo();
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    private void eventInsertLine() {
        if (getMainPanel() != null) {
            LinkUpdater linksUpdater = new LinkUpdater();
            linksUpdater.update(LinkUpdater.ENTER_MODE);
        }
    }

    private void eventRemoveLine() {
        if (getMainPanel() != null) {
            LinkUpdater linksUpdater = new LinkUpdater();
            linksUpdater.update(LinkUpdater.BACKSPACE_MODE);
        }
    }

    private void eventSelectAll() {
        if (getMainPanel() != null) {
            getTextArea().selectAll();
        }
    }

    private void eventStart() {
        if (getMainPanel() != null) {
            if (getMainPanel().isFinished()) {
                getMainPanel().resetValues();
            }
            // Precisa adicionar o Swingworker em uma nova thread para que ele execute!!
            // Vai entender.
            // Duas instÃ¢ncias de Swingworker diferentes não estavam rodando ao mesmo mesmo. O código abaixo solucionou o problema.
            new Thread(new LexAnalyzerWorker(this.getMainPanel(), true)).start();
        }
    }

    private void eventStop() {
        if (getMainPanel() != null) {
            this.getMainPanel().setLinePosition(0);
            this.getMainPanel().setPause(true);
            getMainPanel().resetValues();
        }
    }

    private void eventPause() {
        if (getMainPanel() != null) {
            getMainPanel().setPause(true);
        }
    }

    private void eventStepByStep() {
        if (getMainPanel() != null) {
            if (getMainPanel().isFinished()) {
                getMainPanel().resetValues();
            }
            this.getMainPanel().setPause(false);
            this.getMainPanel().setStepByStepMode(true);
            try {
                new Thread(new LexAnalyzerWorker(this.getMainPanel(), true)).start();
            } catch (Exception ex) {
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void eventCloseWindow() {
        fileEventsHelper.eventCloseWindow();
    }

    public void eventCloseTab() {
        if (getMainPanel() != null) {
            fileEventsHelper.tryToCloseTab(getMainPanel());
        }
    }

    private void eventOpen() {
        fileEventsHelper.eventOpen();
    }

    private void eventImport() {
        if (getMainPanel() != null) {
            fileEventsHelper.eventImport();
        }

    }

    private void eventSave() {
        if (getMainPanel() != null) {
            fileEventsHelper.saveOrSaveAs(getMainPanel());
        }
    }

    private void eventSaveAs() {
        if (getMainPanel() != null) {
            fileEventsHelper.saveAs(getMainPanel());
        }
    }

    private void eventNew() {
        fileEventsHelper.eventNew();
    }

    private void eventGoTo() {
        if (getMainPanel() != null) {
            String lineNumberText = Text.get("general.line_number");
            String actionName = Text.get(actionCommandName);
            String nLinhaStr = JOptionPane.showInputDialog(null, lineNumberText, Text.get(actionCommandName), 3);

            if (nLinhaStr != null) {
                try {
                    int nLine = Integer.parseInt(nLinhaStr);
                    getTextArea().setCaretPosition(getTextArea().getLineStartOffset(nLine));
                } catch (NumberFormatException ex) {
                    String error = Text.get("error.invalid_line_number");
                    JOptionPane.showMessageDialog(null, error, actionName, 0);
                } catch (BadLocationException ex) {
                    String error = Text.get("error.invalid_line_number");
                    JOptionPane.showMessageDialog(null, error, actionName, 0);
                }
            }
        }
    }

    private void eventSetSpeed() {
        if (getMainPanel() != null) {
            getMainPanel().setEditSpeedPressed();
        }
    }

    public void executeAction() {
        if (actionCommandName.equals(MenuActions.ACT_OPEN)) {
            eventOpen();
        } else if (actionCommandName.equals(MenuActions.ACT_PASTE)) {
            eventPaste();
        } else if (actionCommandName.equals(MenuActions.ACT_COPY)) {
            eventCopy();
        } else if (actionCommandName.equals(MenuActions.ACT_CUT)) {
            eventCut();
        } else if (actionCommandName.equals(MenuActions.ACT_DELETE)) {
            eventDelete();
        } else if (actionCommandName.equals(MenuActions.ACT_UNDO)) {
            eventUndo();
        } else if (actionCommandName.equals(MenuActions.ACT_INSERT_LINE)) {
            eventInsertLine();
        } else if (actionCommandName.equals(MenuActions.ACT_REMOVE_LINE)) {
            eventRemoveLine();
        } else if (actionCommandName.equals(MenuActions.ACT_IMPORT)) {
            eventImport();
        } else if (actionCommandName.equals(MenuActions.ACT_START)) {
            eventStart();
        } else if (actionCommandName.equals(MenuActions.ACT_GOTO)) {
            eventGoTo();
        } else if (actionCommandName.equals(MenuActions.ACT_NEW)) {
            eventNew();
        } else if (actionCommandName.equals(MenuActions.ACT_STOP)) {
            eventStop();
        } else if (actionCommandName.equals(MenuActions.ACT_STEP_BY_STEP)) {
            eventStepByStep();
        } else if (actionCommandName.equals(MenuActions.ACT_PAUSE)) {
            eventPause();
        } else if (actionCommandName.equals(MenuActions.ACT_SET_DELAY)) {
            eventSetSpeed();
        } else if (actionCommandName.equals(MenuActions.ACT_REDO)) {
            eventRedo();
        } else if (actionCommandName.equals(MenuActions.ACT_EXIT)) {
            eventCloseWindow();
        } else if (actionCommandName.equals(MenuActions.ACT_SAVE)) {
            eventSave();
        } else if (actionCommandName.equals(MenuActions.ACT_SAVE_AS)) {
            eventSaveAs();
        } else if (actionCommandName.equals(MenuActions.ACT_SELECT_ALL)) {
            eventSelectAll();
        } else if (actionCommandName.equals(MenuActions.ACT_ABOUT)) {
            About about = new About(null, true);
            about.setVisible(true);
        } else {
            // Nenhum comando encontrando
            throw new IllegalArgumentException("Nenhum comando encontrado com esse nome!");
        }
    }
}
