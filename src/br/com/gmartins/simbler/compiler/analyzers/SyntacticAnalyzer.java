/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gmartins.simbler.compiler.analyzers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.gmartins.simbler.components.MainPanel;
import br.com.gmartins.simbler.helpers.RegexMatcher;
import br.com.gmartins.simbler.instructions.Builder;
import br.com.gmartins.simbler.instructions.Instruction;
import br.com.gmartins.simbler.instructions.InstructionRegex;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicDetails;
import br.com.gmartins.simbler.mnemonics.helpers.MnemonicsMap;

/**
 *
 * @author Guilherme
 */
public class SyntacticAnalyzer {

    private final MainPanel panel;
    private List<Instruction> mnemonicList;
    private Builder builder = new Builder();
    private boolean acceptable = true;

    public SyntacticAnalyzer(MainPanel mainPanel) {
        this.panel = mainPanel;
    }

    /**
     * Retorna se o código é aceitável, baseado nos testes efetuados.
     *
     * @return true, caso o teste seja aceito, falso caso contrário
     */
    public boolean isAcceptable() {
        return acceptable;
    }

    /**
     * Define se o código é aceitável ou não.
     *
     * @param acceptable
     */
    public void setAcceptable(boolean acceptable) {
        this.acceptable = acceptable;
    }

    public void analyzeLine(String formattedLine, int position) {
        if (mnemonicList == null) {
            this.mnemonicList = new ArrayList<Instruction>();
        }
        if (isValid(formattedLine) == false) {
            this.setAcceptable(false);
            // Quando o comando for inválido, adiciona uma linha em branco só para que
            // Não ocorra problemas na hora da geração dos ToolTip
            mnemonicList.add(builder.getBuiltInstruction("", panel, position));
        } else {
            this.setAcceptable(true);
            mnemonicList.add(builder.getBuiltInstruction(formattedLine, panel, position));
        }
    }

    private boolean isValid(String formattedLine) {
        MnemonicsMap map = MnemonicsMap.getInstance();
        // Pega a primeira palavra da linha e tenta recuperar com este nome um objeto do mapa.
        MnemonicDetails mnemonic = map.getMnemonicsMap().get(formattedLine.split(" ")[0]);
        // Se conseguir, faz a verificação com o Regex definido nessa instrução.
        if (mnemonic != null) {
            if (regexTest(mnemonic, formattedLine) == false) {
                return false;
            } else {
                return true;
            }
        }
        // Caso não consiga recuperar nenhum objeto, faz a verifição do mapa de regex todo.
        for (Map.Entry<String, MnemonicDetails> m : map.getMnemonicsMap().entrySet()) {
            if (regexTest(m.getValue(), formattedLine) == true) {
                return true;
            }
        }
        return false;
    }

    public boolean regexTest(MnemonicDetails details, String linha) {
        DataTypeRegex dataType = new DataTypeRegex(this.panel);
        for (InstructionRegex regex : details.getRegexList()) {
            /** If regex works with numbers, treats it dynamically */
            if (regex.hasNumbers()) {
                /** Get the current data type and the respective regex */
                String text = regex.getOwner() + InstructionRegex.RX_SPACES_BETWEEN + dataType.getNumbersRegex();
                /** If text matches, return true, since its a valid instruction */
                if (RegexMatcher.matches(text + InstructionRegex.RX_COMMENTS, linha)) {
                    return true;
                }
            } else {
                if (RegexMatcher.matches(regex.getRegex(true) + InstructionRegex.RX_COMMENTS, linha)) {
                    return true;
                }
            }

        }
        return false;
    }

    public List<Instruction> getMnemonicList() {
        return mnemonicList;
    }
}
