package me.dort.calc.ast.lexer;

public abstract class Token {

    private final String literalString;
    private final int location;

    public Token(String literalString, int location) {
        this.literalString = literalString;
        this.location = location;
    }

    public String getLiteralString() {
        return literalString;
    }

    public int getLocation() {
        return location;
    }
}
