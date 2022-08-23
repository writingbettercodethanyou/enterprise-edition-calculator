package me.dort.calc.ast.binary;

import me.dort.calc.ast.Expression;

public class ExponentExpression extends BinaryExpression {

    public ExponentExpression(Expression firstOperand, Expression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public double evaluate() {
        return Math.pow(firstOperand.evaluate(), secondOperand.evaluate());
    }
}
