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
package br.com.gmartins.simbler.components.autocomplete;

import br.com.gmartins.simbler.Principal;
import br.com.gmartins.simbler.components.CodeTextArea;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicDetails;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicsMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.text.BadLocationException;

/**
 * 
 * @author Guilherme
 */
public class AutoCompleteFilter extends AbstractListModel {

    private List items;
    private List filterItems;
    private String termTyped = "";

    public AutoCompleteFilter() {
        super();
        this.items = this.getListData();
        this.filterItems = this.getListData();
    }
    
    @Override
    public Object getElementAt(int index) {
        if (index < filterItems.size()) {
            return filterItems.get(index);
        } else {
            return null;
        }
    }

    @Override
    public int getSize() {
        return filterItems.size();
    }

    public void addElement(Object o) {
        items.add(o);
        refilterList();

    }

    public String getTermTyped() {
        return termTyped.toUpperCase();
    }

    private List getListData() {
        Map<String, MnemonicDetails> lstMap = MnemonicsMap.getInstance().getMnemonicsMap();
        List<String> list = new ArrayList<String>();
        for (Map.Entry<String, MnemonicDetails> entry : lstMap.entrySet()) {
            if (entry.getKey().equals("GeneralCommands") == false) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

    public void refilterList() {
        try {
            CodeTextArea field = Principal.getInstance().getTextArea();
            int currentLine = field.getCurrentLineNumber();
            int startOffset = field.getLineStartOffset(currentLine);
            int endOffset = field.getLineEndOffset(currentLine);
            int stringLength = endOffset - startOffset;

            String text = field.getText(startOffset, stringLength);
            filterItems.clear();

            String[] words = text.split(" ");
            // Se houver apenas uma palavra, busca a lista de comandos baseado nesta palavra

            // Se o termo estiver em branco ou conter quebra de linha, define como vazio
            // para que todos os comandos apareçam na lista
            if (text.isEmpty() || text.contains("\n")) {
                termTyped = "";
            } else if (words.length == 1) {
                termTyped = words[0];
                if (text.endsWith(" ")) {
                    termTyped = "";
                }
                // Se houver mais de uma palavra
            } else if (words.length > 1) {
                termTyped = words[words.length - 1];
                if (text.endsWith(" ")) {
                    termTyped = "";
                }
            }
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).toString().startsWith(getTermTyped(), 0)) {
                    filterItems.add(items.get(i));
                }
            }
            fireContentsChanged(this, 0, getSize());
        } catch (BadLocationException ex) {
            Logger.getLogger(CommandList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
