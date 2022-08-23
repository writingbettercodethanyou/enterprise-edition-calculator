package me.dort.calc.ast.binary;

import me.dort.calc.ast.Expression;

public class ModuloDoubleOperation extends BinaryOperation {

    public ModuloDoubleOperation(Expression firstOperand, Expression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public double evaluate() {
        return firstOperand.evaluate() % secondOperand.evaluate();
    }
}
