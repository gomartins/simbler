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
package br.com.gmartins.simbler.importer;

import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.components.datatype.DataType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Guilherme
 */
public class FileOpener {

    private String SourceFile;
    private JTextComponent component;
    private MainPanel mainPanel;

    public FileOpener(String SourceFile, JTextComponent component) {
        this.SourceFile = SourceFile;
        this.component = component;
    }

    public FileOpener(File file, JTextComponent component) {
        this(file.getAbsolutePath(), component);
    }

    public void setMainPanel(MainPanel panel) {
        this.mainPanel = panel;
    }

    public void load() throws FileNotFoundException {
        if (new File(this.SourceFile).exists() == false) {
            throw new FileNotFoundException("Arquivo não encontrado!");
        }
        try {
            // Redefine o strCmd
            StringBuilder strCmd = new StringBuilder();
            Scanner scanner = new Scanner(new FileInputStream(SourceFile));

            String firstLine = null;
            if (scanner.hasNextLine()) {
                firstLine = scanner.nextLine();
                if (firstLine.contains("$")) {
                    String dataType = firstLine.replace("$", "").trim();
                    this.mainPanel.getDataTypeInput().setDataType(DataType.valueOf(dataType));
                } else {
                    strCmd.append(firstLine);
                    if (scanner.hasNextLine()) {
                        strCmd.append("\n");
                    }
                }
            }

            while (scanner.hasNextLine()) {
                strCmd.append(scanner.nextLine());
                if (scanner.hasNextLine()) {
                    strCmd.append("\n");
                }
            }
            // Fecha o reader
            scanner.close();
            component.setText(strCmd.toString());
            mainPanel.setInitialCode(strCmd.toString());
        } catch (IOException ex) {
            Logger.getLogger(FileOpener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
