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
        lexemes.put("_if_", Pattern.compile("^_if_$"));
        lexemes.put("ELSE", Pattern.compile("^ELSE$"));
        lexemes.put("WHILE", Pattern.compile("^WHILE$"));
        lexemes.put("DO", Pattern.compile("^DO$"));
        lexemes.put("_for_", Pattern.compile("^FOR$"));
        lexemes.put("DIV", Pattern.compile("^,$"));
        lexemes.put("PRINT", Pattern.compile("^PRINT$"));
    }
}