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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Guilherme
 */
public class RegexMatcher {

    public static boolean matches(String pattern, String cmd) {
        Pattern valueExists = Pattern.compile(pattern);
        //Pattern valueExists = Pattern.compile(pattern + "\\s{0,9}?");
        // \\s{0,9}? - \\s = espaço em branco - Aceita espaços em brancos de 0 a 9 caracteres,
        // evitando que o editor considere o comando errado
        // por causa de espaços extras no fim do comando. o caracter "?" torna esse espaço em branco
        // "opcional", podendo haver ou não.
        Matcher m = valueExists.matcher(cmd);
        return m.matches();
    }

    public static String getMatch(String pattern, String cmd) {
        Pattern valueExists = Pattern.compile(pattern);
        Matcher m = valueExists.matcher(cmd);
        if (m.matches()) {
            return m.group();
        }
        return null;
    }
}
