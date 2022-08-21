package me.dort.calc.ast.lexer;

public enum Operator {

    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/'),
    MODULO('%'),
    EXPONENT('^'),
    GROUP_START('('),
    GROUP_END(')');

    private final char representation;

    Operator(char representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return Character.toString(representation);
    }
}
