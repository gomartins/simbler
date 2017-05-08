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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import br.com.gmartins.simbler.Principal;
import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.helpers.FileFilterSimbler;
import br.com.gmartins.simbler.helpers.FileSaver;
import br.com.gmartins.simbler.importer.EniacCodeConverter;
import br.com.gmartins.simbler.importer.FileFilterEniac;
import br.com.gmartins.simbler.properties.PropertiesConstants;
import br.com.gmartins.simbler.properties.Text;

/**
 *
 * @author Guilherme
 */
public class FileEventsHelper {

    private Text properties = new Text();
    private boolean actionCloseCancelled;
    private JFileChooser saveAsFileChooser;
    private JFileChooser openFileChooser;

    private MainPanel getMainPanel() {
        if (Principal.getInstance().getMainPanel() == null) {
            JOptionPane.showMessageDialog(null, Text.get("general.before_execute"));
            return null;
        }
        return Principal.getInstance().getMainPanel();
    }

    /**
     * Este método gera o Painel de escolha de arquivo. Usado para abrir
     * arquivos com extensão ".sbl"
     *
     * @return
     */
    private JFileChooser getOpenFileChooser() {
        if (openFileChooser == null) {
            openFileChooser = new JFileChooser(properties.getProperty(PropertiesConstants.LAST_PATH));
            openFileChooser.setAcceptAllFileFilterUsed(false); // Retira "All Files"
            openFileChooser.addChoosableFileFilter(new FileFilterSimbler()); // Adiciona o Filtro
        }
        return openFileChooser;
    }

    /**
     * Este método gera o Painel de salvamento de arquivo.
     *
     * @return
     */
    private JFileChooser getSaveAsFileChooser() {
        if (saveAsFileChooser == null) {
            saveAsFileChooser = new JFileChooser(properties.getProperty(PropertiesConstants.LAST_PATH)) {

                @Override
                public void approveSelection() {
                    File f;
                    String path = super.getSelectedFile().getAbsolutePath();
                    if (path.endsWith(".sbl")) {
                        f = new File(path);
                    } else {
                        f = new File(path + ".sbl");
                    }
                    if (f.exists()) {
                        int option = JOptionPane.showConfirmDialog(null, Text.get("general.replace_file")
                                + "\n" + f.getName(), Text.get("menu.save_as"), 0, 1);
                        switch (option) {
                            case JOptionPane.NO_OPTION:
                                return;
                            case JOptionPane.YES_OPTION:
                                super.approveSelection();
                        }
                    } else {
                        super.approveSelection();
                    }

                }
            }; // Fim JFileChooser
            saveAsFileChooser.setFileFilter(new FileFilterSimbler());
            saveAsFileChooser.setAcceptAllFileFilterUsed(false); // Retira "All Files"
        }
        return saveAsFileChooser;
    }

    public void eventNew() {
        // Cria um novo Painel
        MainPanel newTab = new MainPanel();
        // Adiciona a nova aba ao JTabbedPane
        Principal.getInstance().getTabbedPane().addTab(Text.get("general.new_file"), newTab);
        // Define o Initial Code como vazio. Será usado ao fechar esta aba, para saber se é necessário salvar o arquivo.
        newTab.setInitialCode("");
        // Seleciona a aba adicionada
        Principal.getInstance().getTabbedPane().setSelectedComponent(newTab);
        newTab.getCodeTextArea().requestFocus();
    }

    public void eventImport() {
        JFileChooser fc = new JFileChooser(properties.getProperty(PropertiesConstants.LAST_PATH)); // Se ja foi aberto, pega o último caminho usado
        fc.setAcceptAllFileFilterUsed(false); // Retira "All Files"
        fc.addChoosableFileFilter(new FileFilterEniac()); // Adiciona o Filtro
        int returnVal = fc.showOpenDialog(fc);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            MainPanel panel = new MainPanel();
            EniacCodeConverter eniac = new EniacCodeConverter(this.getMainPanel());
            // Define o diretório pai do arquivo selecionado como último caminho usado.
            properties.setProperty(PropertiesConstants.LAST_PATH, fc.getSelectedFile().getParent());
            eniac.setSourceFile(fc.getSelectedFile());
            eniac.execute();
            Principal.getInstance().getTabbedPane().addTab(fc.getSelectedFile().getName(), panel);
            Principal.getInstance().getTabbedPane().setSelectedComponent(panel);
        }
    }

    /**
     * Método acionado ao ser clicado no menu abrir.
     */
    public void eventOpen() {
        int returnVal = getOpenFileChooser().showOpenDialog(Principal.getInstance());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = getOpenFileChooser().getSelectedFile();
            JTabbedPane tabbedPane = Principal.getInstance().getTabbedPane();
            // Verifica se já existe uma tab com esse nome, se sim apenas seleciona a tab aberta
            for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                if (tabbedPane.getTitleAt(i).equals(file.getName())) {
                    tabbedPane.setSelectedIndex(i);
                    return;
                }
            }

            properties.setProperty(PropertiesConstants.LAST_PATH, file.getParent());

            final MainPanel panel = new MainPanel();
            try {
                panel.setFile(file);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileEventsHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Define o código inicial para saber se deve ou não ser salvo posteriormente.
            Principal.getInstance().getTabbedPane().addTab(file.getName(), panel);
            // Seleciona a última tab aberta
            Principal.getInstance().getTabbedPane().setLastTabSelected();

        }
    }

    /**
     * Este método atualiza as configurações do paineis após o salvamento.
     *
     * Aqui é atualizado o nome da aba, o código inicial (que deve ser
     * atualizado após salvar o arquivo, pois nele que se baseia o método
     * isCodeChanged de MainPanel para saber se o arquivo foi modificado ou
     * não), além do próprio local do arquivo, no caso de ser usado pelo comando
     * Save As, ou Salvar como.
     *
     * @param panel
     * @param file
     */
    private void updatePanelSettings(MainPanel panel, File file) {
        JTabbedPane tabbedPane = Principal.getInstance().getTabbedPane();
        // Por segurança, seleciona a tab passada como parÃ¢metro.
        Principal.getInstance().getTabbedPane().setSelectedComponent(panel);
        int selectedIndex = tabbedPane.getSelectedIndex();
        String fileName = file.getName().replace(".sbl", "");
        fileName += ".sbl";

        tabbedPane.setTitleAt(selectedIndex, fileName);
        panel.setFilePath(file.getAbsolutePath());
        panel.setInitialCode(panel.getCodeTextArea().getText());
    }

    /**
     * Evento usado ao clicar no "X" ou "Fechar" do JFrame Principal. Este
     * evento faz com que seja verificada e fechada aba por aba em execução no
     * programa.
     *
     * Caso ocorra em algum momento, o pressionamento de uma ação de
     * cancelamento durante o salvamento das abas, o processo ativa o
     * actionCloseCancelled e para o fechamento das abas.
     */
    public void eventCloseWindow() {
        JTabbedPane tabbed = Principal.getInstance().getTabbedPane();
        while (tabbed.getTabCount() != 0) {
            // Fica no while até conseguir fechar todas as abas
            tryToCloseTab(getMainPanel());

            // Se ocorrer do evento actionCloseCancelled ser ligado, para a tentativa de fechar as abas
            if (actionCloseCancelled) {
                return;
            }
        }
        System.exit(0);
    }

    /**
     * Método acionado ao fechar uma aba. Ele verifica se o código foi alterado
     * e se há necessidade de salvar. Se sim, pergunta ao usuário se ele deseja
     * salvar.
     *
     * Dependendo da ação do usuário ele salva ou não o arquivo.
     *
     * @param panel
     */
    public void tryToCloseTab(MainPanel panel) {
        this.actionCloseCancelled = false;
        // Verifica se o código foi modificado
        if (shouldSave(panel, false)) {
            // Mostra o Dialog
            boolean salvar = shouldSave(panel, true);
            
            if(actionCloseCancelled) {
            	return;
            }
            
            if (salvar == false) {
                removeTab(panel);
            } else if (salvar == true) {
                saveOrSaveAs(panel);
                if (actionCloseCancelled) {
                    return;
                }
                removeTab(panel);
            }
        } else {
            removeTab(panel);
        }
    }

    /**
     * Verifica se o painel tem necessidade de ser salvo. A verificação é feita
     * baseada nos seguintes critérios:
     *
     * Se o código inicial (
     *
     * @see MainPanel.setInitialCode(String txt)) não tiver sido alterado, não
     * há necessidade de salvar. Retorna false.
     *
     * Caso o parÃ¢metro showDialog seja true, é mostrado um Dialog perguntando
     * se o usuário deseja salvar.
     *
     * Se o usuário escolher sim, retorna true.
     *
     * Se o usuário escolher não, retorna false.
     *
     * Se o usuário fechar o dialog, ou clicar em cancelar, retorna false e
     * ativa actionCloseCancelled. Quando está propriedade estiver ativa, indica
     * que deve para o evento de fechar, caso a pessoa tenha clicado no X
     * (Fechar) principal do JFrame, isso faz com que seja interrompido quando
     * for clicado em cancelar, ao invés de continuar fechando todas as abas.
     *
     * @param panel Painel a ser salvo
     * @param showDialog true, mostra o dialogo para o usuário escolher se quer
     * salvar, false não mostra.
     * @return true se houver necessidade de salvar, false se não
     */
    public boolean shouldSave(MainPanel panel, boolean showDialog) {
        int option = JOptionPane.OK_OPTION;
        if (panel.isCodeChanged() == false) {
            return false;
        }
        if (showDialog) {
            option = JOptionPane.showConfirmDialog(null, Text.get("general.save_file"), Text.get("menu.save"), 1, 1);
        }
        if (option == JOptionPane.OK_OPTION) {
            return true;
        } else if (option == JOptionPane.NO_OPTION) {
            return false;
        } else if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
            actionCloseCancelled = true;
        }
        return false;
    }

    /**
     * Remove a tab (MainPainel) especificada.
     *
     * @param panel Tab a ser removida.
     */
    private void removeTab(MainPanel panel) {
        JTabbedPane tabbed = Principal.getInstance().getTabbedPane();
        tabbed.remove(panel);
    }

    /**
     * Verifica automaticamente se deve usar o Save ou o Save As para salvar o
     * arquivo.
     *
     * Se MainPanel.getFilePath() == null - Então deve ser usado o Save As, pois
     * o arquivo não foi salvo ainda.
     *
     * Se retornar diferente de null, indica que o arquivo já foi salvo, então
     * usa apenas o Save.
     *
     * @param panel Painel a ser salvo.
     */
    public void saveOrSaveAs(MainPanel panel) {
        // Se o arquivo ainda não tiver sido salvo
        if (this.getMainPanel().getFilePath() == null) {
            saveAs(panel);
        } else {
            // Se o arquivo já tiver sido salvo
            save(panel);
        }
    }

    /**
     * Método usado quando o arquivo já foi salvo alguma vez. Apenas pega o
     * próprio caminho onde o arquivo está localizado e salva novamente.
     *
     * @param panel Painel a ser salvo.
     */
    private void save(MainPanel panel) {
        FileSaver fs = new FileSaver(panel);
        fs.setPath(panel.getFilePath().replace(FileSaver.FILE_EXTENSION, ""));
        fs.saveFile();
        this.updatePanelSettings(panel, new File(panel.getFilePath()));
    }

    /**
     * Método usado quando o arquivo ainda não foi salvo, ou quando o usuário
     * clica em Save As.
     *
     * Salva o arquivo em um local novo.
     *
     * @param panel Painel a ser salvo.
     */
    public void saveAs(MainPanel panel) {
        // InÃ­cio JFileChooser
        int returnVal = this.getSaveAsFileChooser().showSaveDialog(Principal.getInstance());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            FileSaver fs = new FileSaver(panel);
            String filePath = getSaveAsFileChooser().getSelectedFile().getAbsolutePath();
            fs.setPath(filePath.replace(".sbl", ""));
            // Define o diretório pai do arquivo selecionado como último caminho usado.
            properties.setProperty(PropertiesConstants.LAST_PATH, getSaveAsFileChooser().getSelectedFile().getParent());
            fs.saveFile();
            this.updatePanelSettings(panel, getSaveAsFileChooser().getSelectedFile());
        } else {
            actionCloseCancelled = true;
        }
    }
}
