package com.company;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Lexer {
    public ArrayList<Token> tokens = new ArrayList<Token>();
    public int len;
    Lexer(String str) {
        TokenValue lex = new TokenValue();
        char[] code_str = str.toCharArray();
        len = code_str.length;
        String tokenStart = "";
        for (int i = 0; i < str.length(); i++) {

            if (code_str[i] == ' ') {
                continue;
            }

            tokenStart += code_str[i];
            String tokenEnd = " ";

            if (i < str.length() - 1) {
                tokenEnd = tokenStart + code_str[i + 1];
            }

            for (String key : lex.lexemes.keySet()) {
                Pattern p = lex.lexemes.get(key);
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