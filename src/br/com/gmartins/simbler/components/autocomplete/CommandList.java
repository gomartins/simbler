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

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Guilherme
 */
public class CommandList extends JList {

    private AutoCompleteFilter filter = new AutoCompleteFilter();

    private CommandList() {
        setModel(filter);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    private static CommandList instance;

    public static CommandList getInstance() {
        if (instance == null) {
            instance = new CommandList();
        }
        return instance;
    }

    public AutoCompleteFilter getFilter() {
        return filter;
    }

    @Override
    public final void setModel(ListModel m) {
        if (!(m instanceof AutoCompleteFilter)) {
            throw new IllegalArgumentException();
        }
        super.setModel(m);
    }
    
    
    public void addItem(Object o) {
        ((AutoCompleteFilter) getModel()).addElement(o);
    }
      
}
