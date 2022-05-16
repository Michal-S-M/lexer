package com.company;
import java.util.Scanner;
import java.lang.*;
//IF ( a ) { b = c + 1 }
public class Main {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите код программы: ");
        String str = in.nextLine();
        in.close();

        Lexer lexer = new Lexer(str);
        System.out.println("длина кода: "+lexer.len+"токенов");
        Parser parser = new Parser(lexer.tokens, lexer.len);

        if (parser.correctCode) { Interpreter interpreter = new Interpreter(lexer.tokens); }
    }
}

// _if_ ( a ) { a1 = 1 + ab + bc + 1 }