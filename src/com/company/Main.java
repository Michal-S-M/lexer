package com.company;
import java.util.Scanner;
import java.lang.*;

public class Main {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите код программы: ");
        String str = in.nextLine();
        in.close();
        //str = "a = 0;";
        Lexer lexer = new Lexer(str);

        Parser parser = new Parser(lexer.tokens, lexer.len);

        if (parser.correctCode) { Interpreter interpreter = new Interpreter(lexer.tokens); }
    }
}