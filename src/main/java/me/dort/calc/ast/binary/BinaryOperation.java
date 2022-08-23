package me.dort.calc.ast.binary;

import me.dort.calc.ast.Expression;

public abstract class BinaryOperation implements Expression {

    protected final Expression firstOperand;
    protected final Expression secondOperand;

    public BinaryOperation(Expression firstOperand, Expression secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }
}
