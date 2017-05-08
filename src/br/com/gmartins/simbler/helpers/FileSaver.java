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

import br.com.gmartins.simbler.components.MainPanel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Guilherme
 */
public class FileSaver {

    private JTextArea cmd;
    private MainPanel panel;
    private String path;

    public void setPath(String path) {
        this.path = path;
    }
    public static final String FILE_EXTENSION = ".sbl";

    public FileSaver(MainPanel panel) {
        this.cmd = panel.getCodeTextArea();
        this.panel = panel;
    }

    public void saveFile() {
        try {
            File f = null;
            if (path.endsWith(FILE_EXTENSION)) {
                f = new File(path);
            } else {
                f = new File(path + FILE_EXTENSION);
            }
            FileWriter outFile = new FileWriter(f);
            PrintWriter out = new PrintWriter(outFile);
            out.println("$" + panel.getDataTypeInput().getDataType().name());
            String[] text = cmd.getText().split("\n");
            for (int i = 0; i < text.length; i++) {
                out.println(text[i]);
            }
            out.close();


        } catch (IOException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
