package me.dort.calc.ast.binary;

import me.dort.calc.ast.Expression;
import me.dort.calc.ast.lexer.Operator;

public class MultiplyExpression extends BinaryExpression {

    public MultiplyExpression(Expression firstOperand, Expression secondOperand) {
        super(Operator.MULTIPLY, firstOperand, secondOperand);
    }

    @Override
    public double evaluate() {
        return firstOperand.evaluate() * secondOperand.evaluate();
    }

    @Override
    public String toString() {
        return "(" + firstOperand.toString() + " * " + secondOperand.toString() + ")";
    }
}
