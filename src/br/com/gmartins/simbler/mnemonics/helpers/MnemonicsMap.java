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

import java.util.Map;
import java.util.TreeMap;

import br.com.gmartins.simbler.properties.Text;

/**
 *
 * @author Guilherme
 */
public class MnemonicsMap {

    private Map<String, MnemonicDetails> mnemonicMap;
    public static final String RX_SPACES_BETWEEN = "\\s+";
    private static final String RX_NUMBERS = "(-?\\d+|[A-F0-9]+)";
    private static final String RX_REGISTERS = "[ABCD]X";
    // \\s* matches zero or more white characters
    // ; matches de ; character (=P)
    // .* matches any character
    public static final String RX_COMMENTS = "(\\s*;\\s*.*)?";

    private MnemonicsMap() {

        MnemonicDetails generalCommands = new MnemonicDetails();
        MnemonicsRegexList generalRegex = new MnemonicsRegexList(null); // Precisa ser Null, se não dá problema na hora de retornar o regex. Caso especial.
        generalCommands.setName("GeneralCommands");
      //  generalRegex.addRegex(RX_NUMBERS, false);
        generalRegex.addRegex("()", false); // Espaços em branco (\n)
        generalCommands.setRegexList(generalRegex.getList());


        MnemonicDetails add = new MnemonicDetails();
        add.setName("ADD");
        add.setDescription(Text.get("cmd.add"), true);
        MnemonicsRegexList addRegex = new MnemonicsRegexList(add);
        addRegex.addRegex("@" + RX_NUMBERS, true, true);
        addRegex.addRegex(RX_NUMBERS, false, true);
        addRegex.addRegex(RX_REGISTERS, false);
        add.setRegexList(addRegex.getList());

        MnemonicDetails dec = new MnemonicDetails();
        dec.setName("DEC");
        dec.setDescription(Text.get("cmd.dec"), true);
        MnemonicsRegexList decRegex = new MnemonicsRegexList(dec);
        decRegex.addRegex("@" + RX_NUMBERS, true, true);
        decRegex.addRegex(RX_REGISTERS, false);
        dec.setRegexList(decRegex.getList());

        MnemonicDetails div = new MnemonicDetails();
        div.setName("DIV");
        div.setDescription(Text.get("cmd.div"), true);
        MnemonicsRegexList divRegex = new MnemonicsRegexList(div);
        divRegex.addRegex("@" + RX_NUMBERS, true, true);
        divRegex.addRegex(RX_NUMBERS, false, true);
        divRegex.addRegex(RX_REGISTERS, false);
        div.setRegexList(divRegex.getList());


        MnemonicDetails hlt = new MnemonicDetails();
        hlt.setName("HLT");
        hlt.setDescription(Text.get("cmd.hlt"), true);
        MnemonicsRegexList hltRegex = new MnemonicsRegexList(null); // Precisa ser Null, se não dá problema na hora de retornar o regex. Caso especial.
        hltRegex.addRegex("HLT", false); // Casa com HLT apenas
        hlt.setRegexList(hltRegex.getList());

        MnemonicDetails inc = new MnemonicDetails();
        inc.setName("INC");
        inc.setDescription(Text.get("cmd.inc"), true);
        MnemonicsRegexList incRegex = new MnemonicsRegexList(inc);
        incRegex.addRegex("@" + RX_NUMBERS, true, true);
        incRegex.addRegex(RX_REGISTERS, false);
        inc.setRegexList(incRegex.getList());

        MnemonicDetails jmp = new MnemonicDetails();
        jmp.setName("JMP");
        jmp.setDescription(Text.get("cmd.jmp"), true);
        MnemonicsRegexList jmpRegex = new MnemonicsRegexList(jmp);
        jmpRegex.addRegex(RX_NUMBERS, true, true); // JMP tem vÃ­nculos com as linhas, então true. Isso será usado pelo ENTER INTELIGÃŠNTE.
        jmp.setRegexList(jmpRegex.getList());

        MnemonicDetails jnz = new MnemonicDetails();
        jnz.setName("JNZ");
        jnz.setDescription(Text.get("cmd.jnz"), true);
        MnemonicsRegexList jnzRegex = new MnemonicsRegexList(jnz);
        jnzRegex.addRegex(RX_NUMBERS, true, true);
        jnz.setRegexList(jnzRegex.getList());

        MnemonicDetails jz = new MnemonicDetails();
        jz.setName("JZ");
        jz.setDescription(Text.get("cmd.jz"), true);
        MnemonicsRegexList jzRegex = new MnemonicsRegexList(jz);
        jzRegex.addRegex(RX_NUMBERS, true, true);
        jz.setRegexList(jzRegex.getList());


        MnemonicDetails jns = new MnemonicDetails();
        jns.setName("JNS");
        jns.setDescription(Text.get("cmd.jns"), true);
        MnemonicsRegexList jnsRegex = new MnemonicsRegexList(jns);
        jnsRegex.addRegex(RX_NUMBERS, true, true);
        jns.setRegexList(jnsRegex.getList());

        MnemonicDetails js = new MnemonicDetails();
        js.setName("JS");
        js.setDescription(Text.get("cmd.js"), true);
        MnemonicsRegexList jsRegex = new MnemonicsRegexList(js);
        jsRegex.addRegex(RX_NUMBERS, true, true);
        js.setRegexList(jsRegex.getList());

        MnemonicDetails jo = new MnemonicDetails();
        jo.setName("JO");
        jo.setDescription(Text.get("cmd.jo"), true);
        MnemonicsRegexList joRegex = new MnemonicsRegexList(jo);
        joRegex.addRegex(RX_NUMBERS, true, true);
        jo.setRegexList(joRegex.getList());

        MnemonicDetails jno = new MnemonicDetails();
        jno.setName("JNO");
        jno.setDescription(Text.get("cmd.jno"), true);
        MnemonicsRegexList jnoRegex = new MnemonicsRegexList(jno);
        jnoRegex.addRegex(RX_NUMBERS, true, true);
        jno.setRegexList(jnoRegex.getList());

        MnemonicDetails load = new MnemonicDetails();
        load.setName("LOAD");
        load.setDescription(Text.get("cmd.load"), true);
        MnemonicsRegexList loadRegex = new MnemonicsRegexList(load);
        loadRegex.addRegex("@" + RX_NUMBERS, true, true);
        loadRegex.addRegex("@" + RX_REGISTERS, false);
        loadRegex.addRegex(RX_NUMBERS, false, true);
        loadRegex.addRegex(RX_REGISTERS, false);
        load.setRegexList(loadRegex.getList());

        MnemonicDetails mul = new MnemonicDetails();
        mul.setName("MUL");
        mul.setDescription(Text.get("cmd.mul"), true);
        MnemonicsRegexList mulRegex = new MnemonicsRegexList(mul);
        mulRegex.addRegex("@" + RX_NUMBERS, true, true);
        mulRegex.addRegex(RX_NUMBERS, false, true);
        mulRegex.addRegex(RX_REGISTERS, false);
        mul.setRegexList(mulRegex.getList());

        MnemonicDetails store = new MnemonicDetails();
        store.setName("STORE");
        store.setDescription(Text.get("cmd.store"), true);
        MnemonicsRegexList storeRegex = new MnemonicsRegexList(store);
        storeRegex.addRegex("@" + RX_NUMBERS, true, true);
        storeRegex.addRegex("@" + RX_REGISTERS, false);
        storeRegex.addRegex(RX_REGISTERS, false);
        store.setRegexList(storeRegex.getList());

        MnemonicDetails sub = new MnemonicDetails();
        sub.setName("SUB");
        sub.setDescription(Text.get("cmd.sub"), true);
        MnemonicsRegexList subRegex = new MnemonicsRegexList(sub);
        subRegex.addRegex("@" + RX_NUMBERS, true, true);
        subRegex.addRegex(RX_NUMBERS, false, true);
        subRegex.addRegex(RX_REGISTERS, false);
        sub.setRegexList(subRegex.getList());


        MnemonicDetails not = new MnemonicDetails();
        not.setName("NOT");
        not.setDescription(Text.get("cmd.not"), true);
        MnemonicsRegexList notRegex = new MnemonicsRegexList(null);
        notRegex.addRegex("NOT", false); // Casa com NOT apenas
        not.setRegexList(notRegex.getList());


        MnemonicDetails nop = new MnemonicDetails();
        nop.setName("NOP");
        nop.setDescription(Text.get("cmd.nop"), true);
        MnemonicsRegexList nopRegex = new MnemonicsRegexList(null);
        nopRegex.addRegex("NOP", false); // Casa com NOT apenas
        nop.setRegexList(nopRegex.getList());


        MnemonicDetails and = new MnemonicDetails();
        and.setName("AND");
        and.setDescription(Text.get("cmd.and"), true);
        MnemonicsRegexList andRegex = new MnemonicsRegexList(and);
        andRegex.addRegex("@" + RX_NUMBERS, true, true);
        andRegex.addRegex(RX_NUMBERS, false, true);
        andRegex.addRegex(RX_REGISTERS, false);
        and.setRegexList(andRegex.getList());

        MnemonicDetails or = new MnemonicDetails();
        or.setName("OR");
        or.setDescription(Text.get("cmd.or"), true);
        MnemonicsRegexList orRegex = new MnemonicsRegexList(or);
        orRegex.addRegex("@" + RX_NUMBERS, true, true);
        orRegex.addRegex(RX_NUMBERS, false, true);
        orRegex.addRegex(RX_REGISTERS, false);
        or.setRegexList(orRegex.getList());

        MnemonicDetails cmp = new MnemonicDetails();
        cmp.setName("CMP");
        cmp.setDescription(Text.get("cmd.cmp"), true);
        MnemonicsRegexList cmpRegex = new MnemonicsRegexList(cmp);
        cmpRegex.addRegex("@" + RX_NUMBERS, true, true);
        cmpRegex.addRegex(RX_NUMBERS, false, true);
        cmpRegex.addRegex(RX_REGISTERS, false);
        cmp.setRegexList(cmpRegex.getList());

        MnemonicDetails db = new MnemonicDetails();
        db.setName("DB");
        db.setDescription(Text.get("cmd.db"), true);
        MnemonicsRegexList dbRegex = new MnemonicsRegexList(db);
        dbRegex.addRegex(RX_NUMBERS, false, true);
        db.setRegexList(dbRegex.getList());


        this.addMnemonic(add, dec, div, hlt, inc, jmp, jnz, jz, jns, js, jo, jno, load, mul, nop, not, store, or, cmp, db, and, sub, generalCommands);


    }
    private static MnemonicsMap instance;

    public static MnemonicsMap getInstance() {
        if (instance == null) {
            instance = new MnemonicsMap();
        }
        return instance;
    }

    private void addMnemonic(MnemonicDetails... mnemonics) {
        if (mnemonicMap == null) {
            mnemonicMap = new TreeMap<String, MnemonicDetails>();
        }
        for (MnemonicDetails m : mnemonics) {
            this.mnemonicMap.put(m.getName(), m);
        }
    }

    public Map<String, MnemonicDetails> getMnemonicsMap() {
        return this.mnemonicMap;
    }
}
