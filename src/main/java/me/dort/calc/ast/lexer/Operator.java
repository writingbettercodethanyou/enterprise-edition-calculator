package me.dort.calc.ast.lexer;

public enum Operator {

    ADD('+', 2),
    SUBTRACT('-', 2),
    MULTIPLY('*', 1),
    DIVIDE('/', 1),
    MODULO('%', 1),
    EXPONENT('^', 0),
    GROUP_START('(', -1),
    GROUP_END(')', -1);

    private final char representation;
    private final int ordinance;

    Operator(char representation, int ordinance) {
        this.representation = representation;
        this.ordinance = ordinance;
    }

    public int getOrdinance() {
        return ordinance;
    }

    @Override
    public String toString() {
        return Character.toString(representation);
    }
}
