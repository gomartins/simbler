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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.gmartins.simbler.Principal;

/**
 *
 * @author VEND-06
 */
public final class InitialSettings {

    private JFrame frame;

    public InitialSettings(Principal frame) {
        this.frame = (JFrame) frame;
        frame.setIconImage(new ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/frame-icon.png")).getImage());

        try {
            UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InitialSettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(InitialSettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InitialSettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(InitialSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        changeInterface();
    }

    public void setBackgroundColor(Color c) {
        frame.getContentPane().setBackground(c);
        UIManager.getDefaults().put("OptionPane.background", c);
        UIManager.put("Panel.background", c);
    }

    public void centralizeFrame() {
        frame.setLocationRelativeTo(null);
    }

    private void changeInterface() {

        UIManager.put("MenuItem.opaque", Boolean.FALSE);
        UIManager.put("Menu.opaque", Boolean.FALSE);
        UIManager.put("MenuBar.opaque", Boolean.FALSE);

        /*
         * Menu.acceleratorDelimiter	String
        Menu.acceleratorFont	Font
        Menu.acceleratorForeground	Color
        Menu.acceleratorSelectionForeground	Color
        Menu.ActionMap	ActionMap
        Menu.arrowIcon	Icon
        Menu.background	Color
        Menu.border	Border
        Menu.borderPainted	Boolean
        Menu.checkIcon	Icon
        Menu.delay	Integer
        Menu.disabledForeground	Color
        Menu.font	Font
        Menu.foreground	Color
        Menu.margin	Insets
        Menu.menuPopupOffsetX	Integer
        Menu.menuPopupOffsetY	Integer
        Menu.opaque	Boolean
        Menu.selectionBackground	Color
        Menu.selectionForeground	Color
        Menu.shortcutKeys	int[ ]
        Menu.submenuPopupOffsetX	Integer
        Menu.submenuPopupOffsetY	Integer
        Menu.textIconGap	Integer
        Menu.useMenuBarBackgroundForTopLevel	Boolean
        menuPressedItemB	Color
        menuPressedItemF	Color
        menuText	Color
        MenuUI	String
         */

        // Cor JComboBox

        //   UIManager.put("ComboBox.background", new ColorUIResource(Color.WHITE));
        //UIManager.put("ComboBox.disabledBackground", new ColorUIResource(Color.LIGHT_GRAY));

        Icon Erro = new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/ErrorCircle-64.png"));
        UIManager.put("OptionPane.errorIcon", Erro);

        Icon Alerta = new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/error_64.png"));
        UIManager.put("OptionPane.warningIcon", Alerta);

        Icon Informacao = new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/info.png"));
        UIManager.put("OptionPane.informationIcon", Informacao);

        Icon Pergunta = new javax.swing.ImageIcon(getClass().getResource("/br/com/gmartins/simbler/images/help.png"));
        UIManager.put("OptionPane.questionIcon", Pergunta);


        //UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Verdana", Font.PLAIN, 14)));

        UIManager.put("OptionPane.messageForeground", new Color(24, 74, 130));

        /*
        //Set new icon for Error message
        uim.put("OptionPane.errorIcon",error);
        
        //Set new icon for Information message
        uim.put("OptionPane.informationIcon",information);
        
        //Set new icon for Question message
        uim.put("OptionPane.questionIcon",question);
        
        //Set new icon for Warning message
        uim.put("OptionPane.warningIcon",warning);
        //---END SETTING NEW ICON---
         */

        /*---------------------------------------------------------------*/
        /*----------------------Eventos Combobox-------------------------*/
        /*---------------------------------------------------------------*/
        UIManager.put("ComboBox.disabledForeground", Color.BLACK);
        /*---------------------------------------------------------------*/

        /*---------------------------------------------------------------*/
        /*--------------------Eventos FileChooser------------------------*/
        /*---------------------------------------------------------------*/
        UIManager.put("FileChooser.lookInLabelMnemonic", new Integer('E'));
        UIManager.put("FileChooser.lookInLabelText", "Examinar em:");
        UIManager.put("FileChooser.saveInLabelText", "Salvar em:");

        UIManager.put("FileChooser.fileNameLabelMnemonic", new Integer('N'));
        UIManager.put("FileChooser.fileNameLabelText", "Nome do arquivo:");

        UIManager.put("FileChooser.filesOfTypeLabelMnemonic", new Integer('T'));
        UIManager.put("FileChooser.filesOfTypeLabelText", "Tipos de arquivo:");

        UIManager.put("FileChooser.upFolderToolTipText", "Um nÃ­vel acima");
        UIManager.put("FileChooser.upFolderAccessibleName", "Um nÃ­vel acima");

        UIManager.put("FileChooser.homeFolderToolTipText", "Ã�rea de trabalho");
        UIManager.put("FileChooser.homeFolderAccessibleName", "Ã�rea de trabalho");

        UIManager.put("FileChooser.newFolderToolTipText", "Nova pasta");
        UIManager.put("FileChooser.newFolderAccessibleName", "Nova pasta");

        UIManager.put("FileChooser.listViewButtonToolTipText", "Lista");
        UIManager.put("FileChooser.listViewButtonAccessibleName", "Lista");

        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Detalhes");
        UIManager.put("FileChooser.detailsViewButtonAccessibleName", "Detalhes");

        UIManager.put("FileChooser.fileNameHeaderText", "Nome");
        UIManager.put("FileChooser.fileSizeHeaderText", "Tamanho");
        UIManager.put("FileChooser.fileTypeHeaderText", "Tipo");
        UIManager.put("FileChooser.fileDateHeaderText", "Data");
        UIManager.put("FileChooser.fileAttrHeaderText", "Atributos");

        UIManager.put("FileChooser.saveButtonText", "Salvar");
        UIManager.put("FileChooser.cancelButtonText", "Cancelar");
        UIManager.put("FileChooser.openButtonText", "Selecionar");

        UIManager.put("FileChooser.acceptAllFileFilterText", "Todos Arquivos (*.*)");

        UIManager.put("FileChooser.openDialogTitleText", "Abrir");
        UIManager.put("FileChooser.saveDialogTitleText", "Salvar");
        /*---------------------------------------------------------------*/


        /*---------------------------------------------------------------*/
        /*--------------------Eventos OptionPane-------------------------*/
        /*---------------------------------------------------------------*/
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        UIManager.put("OptionPane.noButtonText", "Não");
        UIManager.put("OptionPane.okButtonText", "Ok");
        UIManager.put("OptionPane.yesButtonText", "Sim");
        /*---------------------------------------------------------------*/
    }
}
