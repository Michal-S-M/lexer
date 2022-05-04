package com.company;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
/*
    public static Map<String, Pattern> lexems = new HashMap<>();
    static
    {

        lexems.put("OP_FOR", Pattern.compile("_for_"));
        lexems.put("OP_WHILE", Pattern.compile("_while_"));
        lexems.put("OP_DO", Pattern.compile("_do_"));
        lexems.put("OP_IF", Pattern.compile("_if_"));
        lexems.put("VAR", Pattern.compile("[a-z][a-z0-9]{0,}"));
        lexems.put("DIGIT", Pattern.compile("0|([1-9][0-9]*)"));
        lexems.put("ASSIGN_OP", Pattern.compile("="));
        lexems.put("OP", Pattern.compile("\\+|-|\\/|\\*"));
        lexems.put("L_BRACKET", Pattern.compile("\\("));
        lexems.put("R_BRACKET", Pattern.compile("\\)"));
        lexems.put("L_BRACE", Pattern.compile("\\{"));
        lexems.put("R_BRACE", Pattern.compile("\\}"));
        lexems.put("COMPARISON_OP", Pattern.compile("\\>|<|\\*"));
    }
    */
    public LinkedList<Token> tokens = new LinkedList<Token>();
    public int len;
    Lexer(String str){
        String[] code_str = {str};
        len = code_str.length;
        TokenValue lex = new TokenValue();
        String str_1 = "";
            for (int j = 0; j < code_str.length; j++){
                for (int i = 0; i < code_str[j].length(); i++) {
                    if (code_str[j].toCharArray()[i] == ' ') {
                            continue;
                    }
                    str_1 += code_str[j].toCharArray()[i];
                    String str_2 = " ";
                    if (i < code_str[j].length() - 1) {
                        str_2 = str_1 + code_str[j].toCharArray()[i + 1];
                    }
                    for (String key : lex.regexp.keySet()) {
                        Pattern p = Pattern.compile(lex.regexp.get(key));
                        Matcher m_1 = p.matcher(str_1);
                        Matcher m_2 = p.matcher(str_2);
                        if (m_1.find() && !m_2.find()) {
                                tokens.add(new Token(key.toString(), str_1));
                                str_1 = "";
                        }
                    }
            }
        }

        for (Token token: tokens)
        {
            System.out.println(token);
        }
    }
}