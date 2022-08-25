package me.dort.calc.ast.binary;

import me.dort.calc.ast.Expression;
import me.dort.calc.ast.Operator;

public class SubtractExpression extends BinaryExpression {

    public SubtractExpression(Expression firstOperand, Expression secondOperand) {
        super(Operator.SUBTRACT, firstOperand, secondOperand);
    }

    @Override
    public double evaluate() {
        return firstOperand.evaluate() - secondOperand.evaluate();
    }

    @Override
    public String toString() {
        return "(" + firstOperand.toString() + " - " + secondOperand.toString() + ")";
    }
}
