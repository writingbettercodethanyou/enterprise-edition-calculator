package me.dort.calc.ast.binary;

import me.dort.calc.ast.Expression;

public abstract class BinaryExpression implements Expression {

    protected Expression firstOperand;
    protected Expression secondOperand;

    public BinaryExpression(Expression firstOperand, Expression secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    public Expression getFirstOperand() {
        return firstOperand;
    }

    public Expression getSecondOperand() {
        return secondOperand;
    }

    public void setFirstOperand(Expression firstOperand) {
        this.firstOperand = firstOperand;
    }

    public void setSecondOperand(Expression secondOperand) {
        this.secondOperand = secondOperand;
    }
}
