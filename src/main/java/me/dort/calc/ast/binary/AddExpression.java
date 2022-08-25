package me.dort.calc.ast.binary;

import me.dort.calc.ast.Expression;
import me.dort.calc.ast.Operator;

public class AddExpression extends BinaryExpression {

    public AddExpression(Expression firstOperand, Expression secondOperand) {
        super(Operator.ADD, firstOperand, secondOperand);
    }

    @Override
    public double evaluate() {
        return firstOperand.evaluate() + secondOperand.evaluate();
    }

    @Override
    public String toString() {
        return "(" + firstOperand.toString() + " + " + secondOperand.toString() + ")";
    }
}
