package me.dort.calc.ast.binary;

import me.dort.calc.ast.Expression;
import me.dort.calc.ast.Operator;

public class ExponentExpression extends BinaryExpression {

    public ExponentExpression(Expression firstOperand, Expression secondOperand) {
        super(Operator.EXPONENT, firstOperand, secondOperand);
    }

    @Override
    public double evaluate() {
        return Math.pow(firstOperand.evaluate(), secondOperand.evaluate());
    }

    @Override
    public String toString() {
        return "(" + firstOperand.toString() + " ^ " + secondOperand.toString() + ")";
    }
}
