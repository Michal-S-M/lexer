package com.company;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите код программы: ");
        String code_str = in.nextLine();
        in.close();
        Lexer lexer = new Lexer(code_str);
    }
}
