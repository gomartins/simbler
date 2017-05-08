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

import br.com.gmartins.simbler.menuhelper.MenuActions;
import br.com.gmartins.simbler.menuhelper.MenuHandler;
import br.com.gmartins.simbler.properties.Text;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.plaf.basic.BasicMenuBarUI;

/**
 * Class used to simplify the maintenance of the MenuBar.
 *
 * @author Guilherme
 */
public class MenuBar extends javax.swing.JFrame {

    private List<MenuItem> menuItems;

    public MenuBar() {
        initComponents();
        changeMenuTexts();
        // Fix the UI Problem
        menuBar.setUI(new BasicMenuBarUI());
        this.populateMenuItems();
        this.bindActions();
    }

    /**
     * Get all the JMenuItems inserted on the JMenu and put them on an ArrayList
     */
    private void populateMenuItems() {
        this.menuItems = new ArrayList<MenuItem>();
        Component[] menuComps = menuBar.getComponents();
        for (int i = 0; i < menuComps.length; i++) {
            if (menuComps[i] instanceof JMenu) {
                JMenu menu = (JMenu) menuComps[i];
                Component[] menuItens = menu.getMenuComponents();
                for (int j = 0; j < menuItens.length; j++) {
                    if (menuItens[j] instanceof MenuItem) {
                        MenuItem item = (MenuItem) menuItens[j];
                        this.menuItems.add(item);
                    } // end if
                } // end for
            } // end if
        } // end for
    }

    /**
     * Bind the default action to the JMenuItems.
     */
    private void bindActions() {
        for (final MenuItem menuItem : this.menuItems) {
            menuItem.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    MenuHandler menuHandler = new MenuHandler(menuItem.getDefinition());
                    menuHandler.executeAction();
                }
            });
        }
    }

    /**
     * Return the built JMenuBar
     *
     * @return
     */
    public JMenuBar getMenu() {
        return this.menuBar;
    }

    private void changeMenuTexts() {

        // Top level menus
        jmExecutar.setText(Text.get("menu.execute"));
        jmEditar.setText(Text.get("menu.edit"));
        jmExecutar.setText(Text.get("menu.execute"));
        jmArquivo.setText(Text.get("menu.file"));
        jmAjuda.setText(Text.get("menu.help"));

        // MENU ARQUIVO
        jmOpen.setDefinition(MenuActions.ACT_OPEN);
        jmImport.setDefinition(MenuActions.ACT_IMPORT);
        jmNovo.setDefinition(MenuActions.ACT_NEW);
        jmSair.setDefinition(MenuActions.ACT_EXIT);
        jmSalvar.setDefinition(MenuActions.ACT_SAVE);
        jmSalvarComo.setDefinition(MenuActions.ACT_SAVE_AS);

        // MENU EXECUTAR

        jmIniciar.setDefinition(MenuActions.ACT_START);
        jmParar.setDefinition(MenuActions.ACT_STOP);
        jmPassoaPasso.setDefinition(MenuActions.ACT_STEP_BY_STEP);
        jmPausar.setDefinition(MenuActions.ACT_PAUSE);
        jmDefinirVelocidade.setDefinition(MenuActions.ACT_SET_DELAY);

        // MENU EDITAR
        jmSelecionarTudo.setDefinition(MenuActions.ACT_SELECT_ALL);
        // jmSelecionarTudo.setToolTipText(SELECIONAR_TUDO_DESCR);
        jmInserirLinha.setDefinition(MenuActions.ACT_INSERT_LINE);
        // jmInserirLinha.setToolTipText(INSERIR_LINHA_DESCR);
        jmRemoverLinha.setDefinition(MenuActions.ACT_REMOVE_LINE);
        // jmRemoverLinha.setToolTipText(REMOVER_LINHA_DESCR);

        jmIrPara.setDefinition(MenuActions.ACT_GOTO);
        //  jmIrPara.setToolTipText(IR_PARA_DESCR);
        jmColar.setDefinition(MenuActions.ACT_PASTE);
        //  jmColar.setToolTipText(COLAR_DESCR);
        jmCopiar.setDefinition(MenuActions.ACT_COPY);
        //  jmCopiar.setToolTipText(COPIAR_DESCR);
        jmCortar.setDefinition(MenuActions.ACT_CUT);
        //  jmCortar.setToolTipText(CORTAR_DESCR);
        jmDeletar.setDefinition(MenuActions.ACT_DELETE);
        //  jmDeletar.setToolTipText(DELETAR_DESCR);
        jmDesfazer.setDefinition(MenuActions.ACT_UNDO);
        //    jmDesfazer.setToolTipText(DESFAZER_DESCR);
        jmRefazer.setDefinition(MenuActions.ACT_REDO);
        //    jmRefazer.setToolTipText(REFAZER_DESCR);

        // MENU AJUDA
        jmAbout.setDefinition(MenuActions.ACT_ABOUT);
    }

    /**
     * Return the MenuItem present on application menu.
     *
     * @param name The ACTION MenuItem name
     * @return
     */
    public MenuItem getItemMenu(String name) {
        Component[] items = menuBar.getComponents();
        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof JMenu) {
                if (getJMenuItem((JMenu) items[i], name) != null) {
                    return getJMenuItem((JMenu) items[i], name);
                }
            }
        }
        return null;
    }

    private MenuItem getJMenuItem(JMenu menu, String menuItemName) {
        Component[] items = menu.getMenuComponents();
        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof MenuItem) {
                if (((MenuItem) items[i]).getDefinition().equals(menuItemName)) {
                    return (MenuItem) items[i];
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar = new javax.swing.JMenuBar();
        jmArquivo = new javax.swing.JMenu();
        jmNovo = new MenuItem();
        jmOpen = new br.com.gmartins.simbler.components.MenuItem();
        jmSalvar = new MenuItem();
        jmSalvarComo = new MenuItem();
        jmImport = new MenuItem();
        jmSair = new MenuItem();
        jmEditar = new javax.swing.JMenu();
        jmDesfazer = new MenuItem();
        jmRefazer = new MenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmCortar = new MenuItem();
        jmCopiar = new MenuItem();
        jmColar = new MenuItem();
        jmDeletar = new MenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmIrPara = new MenuItem();
        jmSelecionarTudo = new MenuItem();
        jmInserirLinha = new MenuItem();
        jmRemoverLinha = new MenuItem();
        jmExecutar = new javax.swing.JMenu();
        jmIniciar = new MenuItem();
        jmPassoaPasso = new MenuItem();
        jmPausar = new MenuItem();
        jmParar = new MenuItem();
        jmDefinirVelocidade = new MenuItem();
        jmAjuda = new javax.swing.JMenu();
        jmAbout = new MenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuBar.setBackground(new java.awt.Color(255, 255, 255));
        menuBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jmArquivo.setText("Arquivo");
        jmArquivo.setContentAreaFilled(false);

        jmNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jmNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-new.png"))); // NOI18N
        jmNovo.setText("Novo");
        jmArquivo.add(jmNovo);

        jmOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jmOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-open.png"))); // NOI18N
        jmOpen.setText("Abrir");
        jmArquivo.add(jmOpen);

        jmSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jmSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-save.png"))); // NOI18N
        jmSalvar.setText("Salvar");
        jmArquivo.add(jmSalvar);

        jmSalvarComo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-save-as.png"))); // NOI18N
        jmSalvarComo.setText("Salvar Como");
        jmArquivo.add(jmSalvarComo);

        jmImport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-import.png"))); // NOI18N
        jmImport.setText("Importar");
        jmArquivo.add(jmImport);

        jmSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-exit.png"))); // NOI18N
        jmSair.setText("Sair");
        jmArquivo.add(jmSair);

        menuBar.add(jmArquivo);

        jmEditar.setText("Editar");
        jmEditar.setContentAreaFilled(false);

        jmDesfazer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jmDesfazer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-undo.png"))); // NOI18N
        jmDesfazer.setText("Desfazer");
        jmEditar.add(jmDesfazer);

        jmRefazer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jmRefazer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-redo.png"))); // NOI18N
        jmRefazer.setText("Refazer");
        jmEditar.add(jmRefazer);
        jmEditar.add(jSeparator1);

        jmCortar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jmCortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-cut.png"))); // NOI18N
        jmCortar.setText("Cortar");
        jmEditar.add(jmCortar);

        jmCopiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jmCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-copy.png"))); // NOI18N
        jmCopiar.setText("Copiar");
        jmEditar.add(jmCopiar);

        jmColar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jmColar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-paste.png"))); // NOI18N
        jmColar.setText("Colar");
        jmEditar.add(jmColar);

        jmDeletar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        jmDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-delete.png"))); // NOI18N
        jmDeletar.setText("Deletar");
        jmEditar.add(jmDeletar);
        jmEditar.add(jSeparator2);

        jmIrPara.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jmIrPara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-go-to.png"))); // NOI18N
        jmIrPara.setText("Ir para...");
        jmEditar.add(jmIrPara);

        jmSelecionarTudo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jmSelecionarTudo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-select-all.png"))); // NOI18N
        jmSelecionarTudo.setText("Selecionar Tudo");
        jmEditar.add(jmSelecionarTudo);

        jmInserirLinha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.ALT_MASK));
        jmInserirLinha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-insert-line.png"))); // NOI18N
        jmInserirLinha.setText("Inserir Linha");
        jmEditar.add(jmInserirLinha);

        jmRemoverLinha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, java.awt.event.InputEvent.ALT_MASK));
        jmRemoverLinha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-remove-line.png"))); // NOI18N
        jmRemoverLinha.setText("Remover Linha");
        jmEditar.add(jmRemoverLinha);

        menuBar.add(jmEditar);

        jmExecutar.setText("Executar");
        jmExecutar.setAutoscrolls(true);
        jmExecutar.setContentAreaFilled(false);

        jmIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-play.png"))); // NOI18N
        jmIniciar.setText("Iniciar");
        jmExecutar.add(jmIniciar);

        jmPassoaPasso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-play-step-by-step.png"))); // NOI18N
        jmPassoaPasso.setText("Passo-a-passo");
        jmExecutar.add(jmPassoaPasso);

        jmPausar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-pause.png"))); // NOI18N
        jmPausar.setText("Pausar");
        jmExecutar.add(jmPausar);

        jmParar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-stop.png"))); // NOI18N
        jmParar.setText("Parar");
        jmExecutar.add(jmParar);

        jmDefinirVelocidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-speed.png"))); // NOI18N
        jmDefinirVelocidade.setText("Definir velocidade");
        jmExecutar.add(jmDefinirVelocidade);

        menuBar.add(jmExecutar);

        jmAjuda.setText("Ajuda");
        jmAjuda.setContentAreaFilled(false);

        jmAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/menu-info.png"))); // NOI18N
        jmAbout.setText("Dúvidas");
        jmAjuda.add(jmAbout);

        menuBar.add(jmAjuda);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private br.com.gmartins.simbler.components.MenuItem jmAbout;
    private javax.swing.JMenu jmAjuda;
    private javax.swing.JMenu jmArquivo;
    private br.com.gmartins.simbler.components.MenuItem jmColar;
    private br.com.gmartins.simbler.components.MenuItem jmCopiar;
    private br.com.gmartins.simbler.components.MenuItem jmCortar;
    private br.com.gmartins.simbler.components.MenuItem jmDefinirVelocidade;
    private br.com.gmartins.simbler.components.MenuItem jmDeletar;
    private br.com.gmartins.simbler.components.MenuItem jmDesfazer;
    private javax.swing.JMenu jmEditar;
    private javax.swing.JMenu jmExecutar;
    private br.com.gmartins.simbler.components.MenuItem jmImport;
    private br.com.gmartins.simbler.components.MenuItem jmIniciar;
    private br.com.gmartins.simbler.components.MenuItem jmInserirLinha;
    private br.com.gmartins.simbler.components.MenuItem jmIrPara;
    private br.com.gmartins.simbler.components.MenuItem jmNovo;
    private br.com.gmartins.simbler.components.MenuItem jmOpen;
    private br.com.gmartins.simbler.components.MenuItem jmParar;
    private br.com.gmartins.simbler.components.MenuItem jmPassoaPasso;
    private br.com.gmartins.simbler.components.MenuItem jmPausar;
    private br.com.gmartins.simbler.components.MenuItem jmRefazer;
    private br.com.gmartins.simbler.components.MenuItem jmRemoverLinha;
    private br.com.gmartins.simbler.components.MenuItem jmSair;
    private br.com.gmartins.simbler.components.MenuItem jmSalvar;
    private br.com.gmartins.simbler.components.MenuItem jmSalvarComo;
    private br.com.gmartins.simbler.components.MenuItem jmSelecionarTudo;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
}
