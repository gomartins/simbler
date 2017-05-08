/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gmartins.simbler.components;

import br.com.gmartins.simbler.properties.Text;
import javax.swing.JMenuItem;

/**
 *
 * @author Guilherme
 */
public class MenuItem extends JMenuItem {
    
    private String definition;
    
    public void setDefinition(String definition) {
        this.definition = definition;
        String menuText = Text.get(definition);
        
        this.setText(menuText);        
        try {
            // Set ToolTip text with command description
            String menuToolTip = Text.get(definition + "_descr");            
            this.setToolTipText(menuToolTip);
        } catch (java.util.MissingResourceException ex) {
            // If description was not found, set the menu text as tooltip
            this.setToolTipText(menuText);            
        }
        
    }
    
    public String getDefinition() {
        return definition;
    }
}
