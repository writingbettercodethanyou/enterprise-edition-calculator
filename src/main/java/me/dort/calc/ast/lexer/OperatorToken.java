package me.dort.calc.ast.lexer;

import me.dort.calc.ast.Operator;

public final class OperatorToken extends Token {

    private final Operator operation;

    public OperatorToken(String literalString, int location, Operator operation) {
        super(literalString, location);
        this.operation = operation;
    }

    public Operator getOperation() {
        return operation;
    }
}
