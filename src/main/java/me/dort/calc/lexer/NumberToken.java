package me.dort.calc.lexer;

public final class NumberToken extends Token {

    private final double value;

    public NumberToken(String literalString, int location, double value) {
        super(literalString, location);
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
