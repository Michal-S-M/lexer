package com.company;

public class ParserExc extends Exception {

    public Token current;
    public String expected;
    public int numLine;
    public int numToken;

    public ParserExc(int numLine, int numToken, Token current, String expected) {
        this.numLine = numLine;
        this.numToken = numToken;
        this.current = current;
        this.expected = expected;
    }

    public void getInfo(int numLine, int numToken, Token current, String expected) {
        System.out.printf("Line: "+numLine+" Token: "+(numToken + 1)+" - Expected: "+expected+" but received: "+current.type+"\n");
    }
}