package com.company;
import java.util.ArrayList;
import java.util.Objects;

public class Parser {

    private final ArrayList<Token> tokens;
    private final int len;
    private int iterator;
    private int curLine;
    private Token curToken;
    public boolean correctCode;

    public Parser(ArrayList<Token> tokens, int len) {
        this.tokens = tokens;
        this.len = len;
        curLine = 0;
        iterator = 0;
        curToken = tokens.get(iterator);
        correctCode = true;

        try {
            this.lang();
        } catch (ParserExc| IndexOutOfBoundsException e) {}

    }

    public void lang() throws ParserExc {
        for (int i = 0; i < len; i++) {
            curLine++;
            expr();
        }
    }
    public void expr() {
        body();
        terminalCheck("ENDL");
    }

    public void body() {
        switch (curToken.type) {
            case "VAR" -> expr_assign();
            case "_if_" -> if_op();
            case "WHILE" -> while_op();
            case "DO" -> do_while_op();
            case "_for_" -> for_op();
            case "PRINT" -> print();
            default -> terminalCheck("VAR");
        }
    }

    public void terminalCheck(String tokenType) {
        try {
            if (!Objects.equals(curToken.type, tokenType)) {
                correctCode = false;
                throw new ParserExc(curLine, iterator, curToken, tokenType);
            }
        } catch (ParserExc e) {
            e.getInfo(curLine, iterator, e.current, e.expected);
            curToken = tokens.get(--iterator);
        }
        curToken = tokens.get(++iterator);
    }

    public boolean body_condition() {
        return switch (curToken.type) {
            case "VAR", "_if_", "_for_", "WHILE", "DO", "PRINT" -> true;
            default -> false;
        };
    }

    public boolean body_condition_do_while() {
        return switch (curToken.type) {
            case "VAR", "_if_", "_for_", "DO", "PRINT" -> true;
            default -> false;
        };
    }

    public void expr_value() {
        switch (curToken.type) {
            case "VAR", "DIGIT" -> value();
            case "L_BC" -> infinity();
            default -> terminalCheck("VAR");
        }
        while ("OP".equals(curToken.type)) {
            terminalCheck("OP");
            value();
        }
    }

    public void value() {
        switch (curToken.type) {
            case "DIGIT" -> terminalCheck("DIGIT");
            case "L_BC" -> infinity();
            default -> terminalCheck("VAR");
        }
    }

    public void infinity() {
        terminalCheck("L_BC");
        expr_value();
        terminalCheck("R_BC");
    }

    public void condition() {
        terminalCheck("VAR");
        terminalCheck("COMPARE_OP");
        expr_value();
    }

    public void condition_in_br() {
        terminalCheck("L_BC");
        condition();
        terminalCheck("R_BC");
    }

    public void if_op() {
        terminalCheck("_if_");
        condition_in_br();
        do {
            body();
        } while (body_condition());
        if ("ELSE".equals(curToken.type)) {
            else_op();
        }
    }

    public void else_op() {
        terminalCheck("ELSE");
        do {
            expr();
        } while (body_condition());
    }

    public void while_op() {
        terminalCheck("WHILE");
        condition_in_br();
        do {
            body();
        } while (body_condition());
    }

    public void do_while_op() {
        terminalCheck("DO");
        do {
            body();
        } while (body_condition_do_while());
        terminalCheck("WHILE");
        condition_in_br();
    }

    public void for_op() {
        terminalCheck("_for_");
        terminalCheck("L_BC");
        assign();
        terminalCheck("DIV");
        condition();
        terminalCheck("DIV");
        assign();
        terminalCheck("R_BC");
        do {
            body();
        } while (body_condition());
    }

    public void assign() {
        terminalCheck("VAR");
        terminalCheck("ASSIGN_OP");
        expr_value();
    }

    public void expr_assign() {
        assign();
        while ("DIV".equals(curToken.type)) {
            terminalCheck("DIV");
            assign();
        }
    }

    public void print() {
        terminalCheck("PRINT");
        if ("L_BC".equals(curToken.type)) {
            terminalCheck("L_BC");
            if ("DIGIT".equals(curToken.type)) {
                terminalCheck("DIGIT");
            } else {
                terminalCheck("VAR");
            }
            terminalCheck("R_BC");
        }
    }
}