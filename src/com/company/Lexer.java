package com.company;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Lexer {
    public ArrayList<Token> tokens = new ArrayList<Token>();
    public int len;
    private static final Map<String, Pattern> lexemes = new HashMap<>();
    static {
        lexemes.put("VAR", Pattern.compile("^[A-Za-z][A-Za-z0-9]*\\w*$"));
        lexemes.put("DIGIT", Pattern.compile("^\\d*$"));
        lexemes.put("ASSIGN_OP", Pattern.compile("^=$"));
        lexemes.put("OP", Pattern.compile("^(-|\\+|\\*|/)$"));
        lexemes.put("L_BC", Pattern.compile("^\\($"));
        lexemes.put("R_BC", Pattern.compile("^\\)$"));
        lexemes.put("ENDL", Pattern.compile("^;$"));
        lexemes.put("COMPARE_OP", Pattern.compile("^(~|<|>|!=)$"));
        lexemes.put("IF", Pattern.compile("^_if_$"));
        lexemes.put("ELSE", Pattern.compile("^_else_$"));
        lexemes.put("WHILE", Pattern.compile("^_while_$"));
        lexemes.put("DO", Pattern.compile("^_do_$"));
        lexemes.put("FOR", Pattern.compile("^_for$"));
        lexemes.put("DIV", Pattern.compile("^,$"));
        lexemes.put("PRINT", Pattern.compile("^PRINT$"));
    }

    Lexer(String str) {
        len = str.toCharArray().length;
        String tokenStart = "";
        for (int i = 0; i < str.length(); i++) {

            if (str.toCharArray()[i] == ' ') {
                continue;
            }

            tokenStart += str.toCharArray()[i];
            String tokenEnd = " ";

            if (i < str.length() - 1) {
                tokenEnd = tokenStart + str.toCharArray()[i + 1];
            }

            for (String key : lexemes.keySet()) {
                Pattern p = lexemes.get(key);
                Matcher m_1 = p.matcher(tokenStart);
                Matcher m_2 = p.matcher(tokenEnd);

                if (m_1.find() && !m_2.find()) {
                    tokens.add(new Token(key, tokenStart));
                    tokenStart = "";
                    break;
                }
            }
        }
        for (Token token: tokens)
        {
            System.out.println(token);
        }
    }
}