package me.dort.calc.calculator;

import me.dort.calc.answer.Answer;
import me.dort.calc.ast.binary.*;

public class Calculator implements ICalculator {

    @Override
    public Answer<Double> performCalculation(double firstOperand, double secondOperand, String operator) {
        BinaryExpression operation;
        switch (operator) {
            case "%":
                operation = new ModuloExpression(() -> firstOperand, () -> secondOperand);
                break;
            case "+":
                operation = new AddExpression(() -> firstOperand, () -> secondOperand);
                break;
            case "-":
                operation = new SubtractExpression(() -> firstOperand, () -> secondOperand);
                break;
            case "*":
                operation = new MultiplyExpression(() -> firstOperand, () -> secondOperand);
                break;
            case "/":
                operation = new DivideExpression(() -> firstOperand, () -> secondOperand);
                break;
            default:
                throw new RuntimeException("Method not implemented!");
        }

        return new Answer<>(operation.evaluate());
    }
}
