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

        Lexer lexer = new Lexer(str);

        Parser parser = new Parser(lexer.tokens, lexer.len);

        if (parser.correctCode) { Interpreter interpreter = new Interpreter(lexer.tokens); }
    }
}
/*
b=((2*(2+2))+2);
///
a1 = 15; bc2 = 2; cd = a1 / bc2; cdc = cd * 2 + 1; a = 2 * (2 + 2); PRINT(888888); PRINT(a1);
///
a = 0; _if_ (a < 1) PRINT(a) a1 = 2;
///
a = 0; b = 0; PRINT(b); FOR ( i = 0 , i < 2 , i = i + 1 ) a = a + 1, b = b - 1; PRINT(a); PRINT(20);
 */