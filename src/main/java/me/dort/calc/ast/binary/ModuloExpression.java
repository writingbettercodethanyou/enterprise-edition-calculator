package me.dort.calc.ast.binary;

import me.dort.calc.ast.Expression;
import me.dort.calc.ast.lexer.Operator;

public class ModuloExpression extends BinaryExpression {

    public ModuloExpression(Expression firstOperand, Expression secondOperand) {
        super(Operator.MODULO, firstOperand, secondOperand);
    }

    @Override
    public double evaluate() {
        return firstOperand.evaluate() % secondOperand.evaluate();
    }

    @Override
    public String toString() {
        return "(" + firstOperand.toString() + " % " + secondOperand.toString() + ")";
    }
}
