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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public class MnemonicsRegexList {

    private List<InstructionRegex> regexList;
    private MnemonicDetails owner;

    public MnemonicsRegexList(MnemonicDetails owner) {
        this.owner = owner;
    }


    /**
     * 
     * @param regex The regex pattern
     * @param linkable The set the regex as linkable - Is attached with a memory address
     * @param hasNumbers If the regex has numbers, set true. Used data analysis
     */
    public void addRegex(String regex, boolean linkable, boolean hasNumbers) {
        if (regexList == null) {
            regexList = new ArrayList<InstructionRegex>();
        }
        InstructionRegex mmenomicRegex = new InstructionRegex(owner);
        mmenomicRegex.setLinkable(linkable);
        mmenomicRegex.setRegex(regex);
        mmenomicRegex.setHasNumbers(hasNumbers);
        this.regexList.add(mmenomicRegex);
    }
    
    /**
     * By default, set HasNumbers = False
     * For setting HasNumbers = True, use the specific method
     * @param regex
     * @param linkable 
     */
    public void addRegex(String regex, boolean linkable) {
        this.addRegex(regex, linkable, false);
    }


    public List<InstructionRegex> getList() {
        return this.regexList;
    }
}
