package com.company;
import java.util.*;
import java.util.regex.Pattern;

public class TokenValue {
    public static final Map<String, Pattern> lexemes = new HashMap<>();
    public TokenValue() {
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
}