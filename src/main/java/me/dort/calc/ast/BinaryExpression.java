package me.dort.calc.ast;

import me.dort.calc.ast.visitor.ExpressionVisitor;

public class BinaryExpression implements Expression {

    private final Operator operator;

    protected Expression firstOperand;
    protected Expression secondOperand;

    public BinaryExpression(Operator operator, Expression firstOperand, Expression secondOperand) {
        this.operator = operator;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitBinaryExpression(this);
    }

    public Operator getOperator() {
        return operator;
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
