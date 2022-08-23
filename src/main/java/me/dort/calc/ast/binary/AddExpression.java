package me.dort.calc.ast.binary;

import me.dort.calc.ast.Expression;

public class AddExpression extends BinaryExpression {

    public AddExpression(Expression firstOperand, Expression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public double evaluate() {
        return firstOperand.evaluate() + secondOperand.evaluate();
    }
}
