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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import br.com.gmartins.simbler.components.FileImporterLoadingBar;
import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.helpers.HexConverter;

/**
 *
 * @author Guilherme
 */
public class EniacCodeConverter extends SwingWorker<Integer, Integer> {

    private JTextArea cmd;
    private File sourceFile;
    private MainPanel panel;
    public EniacCodeConverter(MainPanel panel) {
        this.cmd = panel.getCodeTextArea();
        this.panel = panel;
    }

    public void setSourceFile(File sourceFile) {
        this.sourceFile = sourceFile;
    }

    private int countLines() {
        int nLines = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile)));
            String vLine;
            while ((vLine = reader.readLine()) != null) {
                nLines++;
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(EniacCodeConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nLines;
    }
    private StringBuilder strCmd;

    private void getCommand(String cmd) {
        if (!cmd.isEmpty()) {
            HexConverter hexToDec = new HexConverter();
            String fWord = "";
            String lWord = "";
            // Comparação feita pois estava causando travamentos quando eram lidas linhas com menos de 3 caracteres
            if (cmd.length() >= 3) {
                fWord = cmd.substring(0, 3).toUpperCase();
                lWord = cmd.substring(3);
            }

            /** Todos os códigos comparados abaixo foram retirados do ENIAC a partir de testes feitos com base nos comandos que eu conhecia,
             * pode ser que hajam mais códigos do que abaixo.
             * Os 3 primeiros digitos do ENIAC tratam-se da definição dos comandos, que provavelmente foi um número escolhido pelo próprio criador
             * Os 3 últimos digitos, podem ser números de valores, por exemplo "JMP 30" - o "30" representa os 3 últimos digitos em Hexadecimal
             * Podem também representar os registradores, como AX, BX e etc, cada registrador tem um número de indentificação prórpio que foi definido pelo
             * criador.
             * Totalizando 6 digitos
             *
             * Para cada linha de comando contido no arquivo .eniac é feita a verificação, caso a comparação seja verdadeira é feita a conversão
             */
            if (fWord.equals("200")) {
                strCmd.append("JMP ");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());

            } else if (fWord.equals("A00")) { // LOAD 5
                strCmd.append("LOAD ");

                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else if (fWord.equals("A02")) { // LOAD @1
                strCmd.append("LOAD @");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else if (fWord.equals("A08")) { // LOAD AX
                strCmd.append("LOAD ");
                strCmd.append(checkRegister(lWord));
            } else if (fWord.equals("A0A")) { // LOAD @BX
                strCmd.append("LOAD @");
                strCmd.append(checkRegister(lWord));
            } else if (fWord.equals("2B0")) {
                strCmd.append("JZ ");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else if (fWord.equals("2A0")) {
                strCmd.append("JNZ ");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else if (fWord.equals("100")) {
                strCmd.append("HLT");
            } else if (fWord.equals("C00")) {
                strCmd.append("SUB ");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else if (fWord.equals("C02")) {
                strCmd.append("SUB @");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else if (fWord.equals("C08")) {
                strCmd.append("SUB ");
                strCmd.append(checkRegister(lWord));
            } else if (fWord.equals("C80")) {
                strCmd.append("ADD ");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else if (fWord.equals("C82")) {
                strCmd.append("ADD @");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else if (fWord.equals("C88")) {
                strCmd.append("ADD ");
                strCmd.append(checkRegister(lWord));
            } else if (fWord.equals("F00")) {
                strCmd.append("MUL ");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else if (fWord.equals("F02")) {
                strCmd.append("MUL @");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else if (fWord.equals("F08")) {
                strCmd.append("MUL ");
                strCmd.append(checkRegister(lWord));
            } else if (fWord.equals("F80")) {
                strCmd.append("DIV ");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else if (fWord.equals("F82")) {
                strCmd.append("DIV @");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else if (fWord.equals("D88")) {
                strCmd.append("INC ");
                strCmd.append(checkRegister(lWord));
            } else if (fWord.equals("D82")) {
                strCmd.append("INC @");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else if (fWord.equals("D08")) {
                strCmd.append("DEC ");
                strCmd.append(checkRegister(lWord));
            } else if (fWord.equals("D02")) {
                strCmd.append("DEC @");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else if (fWord.equals("B88")) {
                strCmd.append("STORE ");
                strCmd.append(checkRegister(lWord));
            } else if (fWord.equals("B82")) {
                strCmd.append("STORE @");
                hexToDec.setHexValue(lWord);
                strCmd.append(hexToDec.convertToDecimal());
            } else {
                hexToDec.setHexValue(cmd);
                strCmd.append("DB ");
                strCmd.append(hexToDec.convertToDecimal());
            }
        } else {
            // Caso a linha esteja vazia, então define vazio em strCmd;
            strCmd.append("");
        }
        // Para toda linha percorrida do arquivo, é adicionado uma quebra de linha
        strCmd.append("\n");
    }

    private String checkRegister(String r) {
        String registerValue = "";
        if (r.equals("000")) {
            registerValue = "AX";
        } else if (r.equals("001")) {
            registerValue = "BX";
        } else if (r.equals("006")) {
            registerValue = "CX";
        } else if (r.equals("007")) {
            registerValue = "DX";
        }
        return registerValue;
    }
    private FileImporterLoadingBar bl = new FileImporterLoadingBar(null, false);

    @Override
    protected Integer doInBackground() throws Exception {
        // Pega o Centro onde o form está aberto
        //int ypos = (pai.getLocation().y + pai.getSize().height / 2) - bl.getSize().height / 2;
        //int xpos = (pai.getLocation().x + pai.getSize().width / 2) - bl.getSize().width / 2;
        //bl.setLocation(xpos, ypos);
        // Mostra Loading Bar
        bl.setVisible(true);
        int LinePosition = 0;
        // Define o valor máximo da Loading Bar para fazer o carregamento
        bl.setMaxValue(countLines());
        try {
            // Redefine o strCmd
            strCmd = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile)));
            String vLine;
            // Enquanto a linha não for null, percore o arquivo pegando comando por comando, convertendo com o getCommand e gravando
            // no StringBuilder strCmd
            while ((vLine = reader.readLine()) != null) {
                getCommand(vLine);
                LinePosition++;
                // Atualiza o valor da barra para fazer o efeito de carregando
                bl.setProgressValue(LinePosition);
            }
            // Fecha o reader
            reader.close();
            // Preenche o JTextArea de Comandos com o Texto convertido do ENIAC
            cmd.setText(strCmd.toString());
            panel.getRegisterManager().resetValues();
            //pai.setTextAreaTxt(strCmd.toString());
        } catch (IOException ex) {
            Logger.getLogger(EniacCodeConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    @Override
    protected void done() {
        // Fecha o JDialog que contém a Loading Bar
        bl.done();
    }
}
