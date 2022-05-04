package com.company;

class Token
{
    public String type;
    public String value;
    public Token(String type, String value)
    {
        this.type = type;
        this.value = value;
    }
    public String toString()
    {
        return "TOKEN[тип=\"" + this.type + "\", значение \"" + this.value + "\"]";
    }


}