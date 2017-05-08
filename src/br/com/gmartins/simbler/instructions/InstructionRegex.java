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
package br.com.gmartins.simbler.instructions;

import br.com.gmartins.simbler.mnemonics.helpers.MnemonicDetails;

/**
 *
 * @author Guilherme
 */
public class InstructionRegex {

    
    // -?1[0-2][0-7]|[0-9]{1,2}     - Aceita de -127 a 127
   // [0-1]{8}                      - Aceita binários de 00000000 a 11111111
    // [0-9A-F]{1,2}                - Hexadecimais de 00 A FF
    
    public static final String RX_SPACES_BETWEEN = "\\s+";
    public static final String RX_NUMBERS = "(-?\\d+|[A-F0-9]+)"; // Check if it's still necessary
    public static final String RX_REGISTERS = "[ABCD]X";
    // \\s* matches zero or more white characters
    // ; matches the ; character (=P)
    // .* matches any character
    public static final String RX_COMMENTS = "(\\s*;\\s*.*)?";
    private String regex;
    private boolean linkable;
    private String owner;
    private boolean numbers;
    
    public boolean hasNumbers() {
        return numbers;
    }
    public void setHasNumbers(boolean numbers) {
        this.numbers = numbers;
    }

    public String getOwner() {
        return owner;
    }

    
    
    /**
     * Construct a new Instruction Regex with the Owner name.
     * The owner name is used to return the regex with the command name.
     * @param owner Set the owner name of this Instruction Regex.
     */
    public InstructionRegex(MnemonicDetails owner) {
        if (owner != null) {
            this.owner = owner.getName();
        }
    }

    /**
     * Set the regex for this Instruction Regex.
     * @param regex 
     */
    public void setRegex(String regex) {
        this.regex = regex;
    }

    /**
     * Returns the regex specified.
     * 
     * If the parameter is true, it returns the command name + the specified regex.
     * Ex.: "ADD\\s+[ABCD]X"
     * 
     * Otherwise, return only the regex:
     * Ex.: "[ABCD]X"
     * 
     * @param withCommandName 
     * @return 
     */
    public String getRegex(boolean withCommandName) {
        if (withCommandName && owner != null) {
            return owner + RX_SPACES_BETWEEN + regex;
        }
        return regex;
    }


    /**
     * Set this regex linkable.
     * This is, when the user activates the "smart enter", it will also update this command line.
     * Updating all linkable commands.
     * @param linkeable 
     */
    public void setLinkable(boolean linkeable) {
        this.linkable = linkeable;
    }

    /**
     * This is used to check if this regex has links with other commands.
     * If yes, the command line will be updated when the user uses the "smart enter".
     * @return linkeable true if the Regex is linkable, false otherwise.
     */
    public boolean isLinkable() {
        return linkable;
    }
}
