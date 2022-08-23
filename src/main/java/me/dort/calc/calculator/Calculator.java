package me.dort.calc.calculator;

import me.dort.calc.answer.Answer;
import me.dort.calc.ast.binary.*;

public class Calculator implements ICalculator {

    @Override
    public Answer<Double> performCalculation(double firstOperand, double secondOperand, String operator) {
        BinaryOperation operation;
        switch (operator) {
            case "%":
                operation = new ModuloDoubleOperation(() -> firstOperand, () -> secondOperand);
                break;
            case "+":
                operation = new AddDoubleOperation(() -> firstOperand, () -> secondOperand);
                break;
            case "-":
                operation = new SubtractDoubleOperation(() -> firstOperand, () -> secondOperand);
                break;
            case "*":
                operation = new MultiplyDoubleOperation(() -> firstOperand, () -> secondOperand);
                break;
            case "/":
                operation = new DivideDoubleOperation(() -> firstOperand, () -> secondOperand);
                break;
            default:
                throw new RuntimeException("Method not implemented!");
        }

        return new Answer<>(operation.evaluate());
    }
}
