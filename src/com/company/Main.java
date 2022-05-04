package com.company;
import java.util.Scanner;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws ParseExc
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите код программы: ");
        String str = in.nextLine();
        in.close();

        Lexer lexer = new Lexer(str);

        Parser parser = new Parser(lexer.tokens, lexer.len);
        parser.lang();

    }
}

// _if_ ( a ) { a1 = 1 + ab + bc + 1 }