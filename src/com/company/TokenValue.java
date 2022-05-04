package com.company;

import java.util.*;
public class TokenValue {
    static Map<String,String> regexp = new HashMap<String,String>();
    public TokenValue (){
        regexp.put("WHILE OP","^[_][w][h][i][l][e][_]$");
        regexp.put("COMPARISON_OP", "^[>|<|~|([=][=])]$");
        regexp.put("L_BRACKET","^\\($");
        regexp.put("R_BRACKET","^\\)$");
        regexp.put("L_BRACE","^\\{$");
        regexp.put("R_BRACE","^\\}$");
        regexp.put("IF OP", "^[_][i][f][_]$");
        regexp.put("VAR", "^[a-z][a-z0-9]*\\w$");
        regexp.put("DIGIT", "^0|([1-9][0-9]*)$");
        regexp.put("OPERATOR", "^[-|+|/|*|%]$");
        regexp.put("ASSIGNMENT OPERATOR", "^=$");
        regexp.put("ENDLINE", "^\\;$");
        regexp.put("DO_OP", "^[_][d][o][_]$");
    }
}
