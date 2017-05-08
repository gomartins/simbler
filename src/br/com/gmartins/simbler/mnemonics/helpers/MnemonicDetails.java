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
package br.com.gmartins.simbler.mnemonics.helpers;

import br.com.gmartins.simbler.instructions.InstructionRegex;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public class MnemonicDetails {

    private String name;
    private List<InstructionRegex> regexList;
    private String descrption;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.descrption = description;
    } 
    
    public void setDescription(String description, boolean addHtmlHeader) {
        if(true) {
             String htmlHeader = "<html> <font face=\"Arial\" size=3 color=\"051E31\"> ";
             String htmlFooter = "</font> </html>";
             description = htmlHeader + description + htmlFooter;
        }
        this.descrption = description;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setRegexList(List<InstructionRegex> regexList) {
        this.regexList = regexList;
    }

    public List<InstructionRegex> getRegexList() {
        return this.regexList;
    }
}
