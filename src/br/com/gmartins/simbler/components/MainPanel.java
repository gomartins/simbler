package br.com.gmartins.simbler.components;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.undo.UndoManager;

import br.com.gmartins.simbler.components.autocomplete.AutoCompletePanel;
import br.com.gmartins.simbler.components.autocomplete.CommandList;
import br.com.gmartins.simbler.components.datatype.DataTypeInputSetter;
import br.com.gmartins.simbler.components.memory.PanelMemory;
import br.com.gmartins.simbler.helpers.Worker;
import br.com.gmartins.simbler.importer.FileOpener;
import br.com.gmartins.simbler.instructions.Instruction;
import br.com.gmartins.simbler.menuhelper.RightClickMenu;
import br.com.gmartins.simbler.properties.Text;

/**
 *
 * @author Guilherme
 */
public class MainPanel extends javax.swing.JPanel {

    private int speed;
    private String filePath;
    private boolean syntaxChecked;
    private boolean paused;
    private boolean stepByStepMode;
    private int linePosition;
    private String initialCode;
    private UndoManager undoManager = new UndoManager();
    private JPopupMenu popup = new JPopupMenu();
    private boolean finished;
    private List<Instruction> commandList;
    private String applicationDelayText = Text.get("general.app_delay");
    private String setApplicationDelayText = Text.get("general.set_app_delay");
    private String errorWhileRunningText = Text.get("error.cannot_run");

    public MainPanel() {
        initComponents();
        this.sliderSpeed.setMaximum(0);
        this.sliderSpeed.setMaximum(300);
        this.speed = 0;
        this.codeTextArea.getDocument().addUndoableEditListener(undoManager);
        // Adiciona o Menu
        RightClickMenu menu = new RightClickMenu();
        menu.addMenuTo(codeTextArea);

    }

    public DataTypeInputSetter getDataTypeInput() {
        return dataTypeInputSetter1;
    }

    public UndoManager getUndoManager() {
        return undoManager;
    }

    public void setInitialCode(String initialCode) {
        this.initialCode = initialCode;
    }

    public String getInitialCode() {
        return initialCode;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        if (finished) {
            this.setLinePosition(0);
        }
        this.finished = finished;
    }

    public boolean isCodeChanged() {
        // Se o código estiver igual ao código inicial, ok, retorna falso.
        // Se o código estiver diferente, então retorna true.
        return !getCodeTextArea().getText().equals(getInitialCode());
    }

    public void setSyntaxChecked(boolean syntaxChecked) {
        this.syntaxChecked = syntaxChecked;
    }

    public boolean isSyntaxChecked() {
        return syntaxChecked;
    }

    public void setCommandList(List<Instruction> commandList) {
        this.commandList = commandList;
    }

    public List<Instruction> getCommandList() {
        return commandList;
    }

    public void executeCommand(int position) {

        Instruction mnemonic = this.getCommandList().get(position);
        if (mnemonic.getExecutable() != null) {
            mnemonic.getExecutable().execute();
        }

    }

    public void setDocumentationVisible(boolean b) {
        if (b == false) {
            popup.setVisible(false);
            return;
        }
        CommandList lst = CommandList.getInstance();
        lst.getFilter().refilterList();
        popup.add(AutoCompletePanel.getInstance(), this);
        int posx = 0;
        int posy = 0;
        if (getCodeTextArea().getCaret().getMagicCaretPosition() != null) {
            posx = getCodeTextArea().getCaret().getMagicCaretPosition().x;
            posy = getCodeTextArea().getCaret().getMagicCaretPosition().y;
        }
        popup.show(this, posx + 5, posy + 93);
        lst.setSelectedIndex(0);
        lst.requestFocus();
    }

    public CodeTextArea getCodeTextArea() {
        return this.codeTextArea;
    }

    public void setFile(File file) throws FileNotFoundException {
        String path = file.getAbsolutePath();
        FileOpener fileOpener = new FileOpener(path, this.getCodeTextArea());
        fileOpener.setMainPanel(this);
        setFilePath(path);
        fileOpener.load();
    }

    /**
     * Set the file path.
     *
     * @param path
     */
    public void setFilePath(String path) {
        this.filePath = path;
    }

    /**
     * Return the current open file path.
     *
     * @return A String with the complete file path.
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     *
     * The Register Manager have methods to change and get registers values.
     *
     * @return The Register Manager.
     */
    public PanelMemory getRegisterManager() {
        return this.registerManager;
    }

    /**
     * Return the Flag Manager. The Flag Manager have methods to change and get
     * overflow, zero, and signal values.
     */
    public PanelMemory getFlagManager() {
        return this.registerManager;
    }

    /**
     * Set the new running speed of the program. This is the speed that the
     * program run through the commands typed on the text area.
     *
     * @param speed The new speed.
     */
    public void setSpeed(int speed) {
        if (speed < 0) {
            this.speed = 0;
            lblVelocidade.setText(this.applicationDelayText + ": 0");
            return;
        }
        this.speed = speed;
        sliderSpeed.setValue(speed / 10);
        lblVelocidade.setText(this.applicationDelayText + ": " + speed);
    }

    /**
     * Get the current running speed of the program.
     *
     * @return The speed.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * This method is called when the user clicks on the "Edit speed button"
     * (The pencil) It shows a dialog to choose the new speed.
     */
    public void setEditSpeedPressed() {
        String value = JOptionPane.showInputDialog(this, this.applicationDelayText, this.setApplicationDelayText, 3);
        if (value != null) {
            int newSpeed = Integer.parseInt(value.toString());
            this.setSpeed(newSpeed);
        }
    }

    public Worker getWorker() {
        // SwingWorkers só podem ser executados uma vez.
        // Ã‰ necessário instanciar um objeto novo toda vez.
        return new Worker(this);
    }

    /**
     * Set the paused status of the program. If it's set to true, the program
     * will stop running where it is.
     *
     * @param b
     */
    public void setPause(boolean b) {
        this.paused = b;
    }

    /**
     * Check if the program is paused.
     *
     * @return true if yes, false otherwise.
     */
    public boolean isPaused() {
        return this.paused;
    }

    /**
     * Turn on/off the Step by Step Mode. This mode run and pause the program at
     * each command. Commonly used to debug.
     *
     * @param b true to turn on, false otherwise.
     */
    public void setStepByStepMode(boolean b) {
        this.stepByStepMode = b;
    }

    /**
     * Check if the program is running on Step by Step Mode.
     *
     * @return true if yes, false otherwise.
     */
    public boolean isStepByStepMode() {
        return stepByStepMode;
    }

    /**
     * Set the program's line to the specified value. The line position is
     * monitored by the register PC.
     *
     * @param pos Line position.
     */
    public void setLinePosition(int pos) {
        this.linePosition = pos;
    }

    /**
     * Get the current line position.
     *
     * @return The line position.
     */
    public int getLinePosition() {
        return linePosition;
    }

    /**
     * This method is used to start the running of the program. This is invoked
     * by the SyntaxVerifier class. Once the syntax is totally verified, it
     * calls this method and if the syntax is fine it starts the program.
     */
    public void runProgram() {
        if (isSyntaxChecked()) {
            // panelDebugArea1.getTextArea().setText("");
            this.setPause(false);
            try {
                // Precisa adicionar o Swingworker em uma nova thread para que ele execute!!
                // Vai entender.
                // Duas instÃ¢ncias de Swingworker diferentes não estavam rodando ao mesmo mesmo. O código abaixo solucionou o problema.
                new Thread(this.getWorker()).start();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, this.errorWhileRunningText + "\n" + ex);
            }
        }
    }

    public void resetValues() {
        this.registerManager.resetValues();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        codeTextArea = new br.com.gmartins.simbler.components.CodeTextArea();
        sliderSpeed = new javax.swing.JSlider();
        dataTypeInputSetter1 = new br.com.gmartins.simbler.components.datatype.DataTypeInputSetter(this);
        registerManager = new br.com.gmartins.simbler.components.memory.PanelMemory(this);
        lblVelocidade = new javax.swing.JLabel();

        jScrollPane1.setViewportView(codeTextArea);

        sliderSpeed.setFocusable(false);
        sliderSpeed.setMaximumSize(null);
        sliderSpeed.setOpaque(false);
        sliderSpeed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderSpeedStateChanged(evt);
            }
        });

        dataTypeInputSetter1.setFocusable(false);

        lblVelocidade.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblVelocidade.setForeground(new java.awt.Color(0, 102, 153));
        lblVelocidade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVelocidade.setToolTipText("Intervalo entre a execução das instruções");
        lblVelocidade.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblVelocidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVelocidadeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(registerManager, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dataTypeInputSetter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVelocidade, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                            .addComponent(sliderSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                        .addGap(21, 21, 21)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblVelocidade, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(sliderSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dataTypeInputSetter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(registerManager, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sliderSpeedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderSpeedStateChanged
        int SliderSpeed = sliderSpeed.getValue() * 10;
        lblVelocidade.setText(this.applicationDelayText + ": " + SliderSpeed);
        this.speed = SliderSpeed;
}//GEN-LAST:event_sliderSpeedStateChanged

    private void lblVelocidadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVelocidadeMouseClicked
        setEditSpeedPressed();

    }//GEN-LAST:event_lblVelocidadeMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.gmartins.simbler.components.CodeTextArea codeTextArea;
    private br.com.gmartins.simbler.components.datatype.DataTypeInputSetter dataTypeInputSetter1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblVelocidade;
    private br.com.gmartins.simbler.components.memory.PanelMemory registerManager;
    private javax.swing.JSlider sliderSpeed;
    // End of variables declaration//GEN-END:variables
}
