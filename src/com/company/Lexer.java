package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer
{
    public static Map<String, Pattern> lexems = new HashMap<>();
    static
    {
        lexems.put("VAR", Pattern.compile("[a-z][a-z0-9]{0,}"));
        lexems.put("DIGIT", Pattern.compile("0|([1-9][0-9]*)"));
        lexems.put("ASSIGN_OP", Pattern.compile("="));
        lexems.put("OP", Pattern.compile("\\+|-|\\/|\\*"));
    }

    Lexer(String code_str) {
        char[] arr_code_str = code_str.toCharArray();
        String resultToken = "";
        List<Token> tokens = new LinkedList<>();
        for (String lexemName : lexems.keySet())
        {
            Matcher m = lexems.get(lexemName).matcher(code_str);
            while (m.find())
            {
                int end = m.end();
                int start = m.start();
                for(int i = start; i < end; i++)
                {
                    resultToken += arr_code_str[i];
                }
                tokens.add(new Token(lexemName, resultToken));
                resultToken = "";
            }
        }
        for (Token token: tokens)
        {
            System.out.println(token);
        }
    }
}