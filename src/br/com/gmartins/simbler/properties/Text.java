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
package br.com.gmartins.simbler.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Guilherme
 */
public class Text {

	private Properties prop = new Properties();

	public String getProperty(String propertyName) {
		return getPropertyFile().getProperty(propertyName);
	}

	public void setProperty(String key, String value) {
		getPropertyFile().setProperty(key, value);
		this.save();
	}

	private Properties getPropertyFile() {
		try {
			prop.load(new FileInputStream(PropertiesConstants.PROPERTIES_FILE_SETTINGS));
		} catch (IOException ex) {
			Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
		}
		return prop;
	}

	private void save() {
		try {
			prop.store(new FileOutputStream(new File(PropertiesConstants.PROPERTIES_FILE_SETTINGS)), null);
		} catch (IOException ex) {
			Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	static String resource = "./resources/messages_pt_BR.xml";

	public static String get(String key) {
		FileInputStream fis = null;
		String obtainedValue = "";
		Properties prop = new Properties();
		try {
			fis = new FileInputStream(resource);
			prop.loadFromXML(fis);
			obtainedValue =  prop.getProperty(key);
		} catch (MissingResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Clear the initially loaded cache to update changes;
		return obtainedValue;
	}
}
