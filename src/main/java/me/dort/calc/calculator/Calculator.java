package me.dort.calc.calculator;

import me.dort.calc.answer.Answer;
import me.dort.calc.ast.binary.*;

public class Calculator implements ICalculator {

    @Override
    public Answer<Double> performCalculation(double firstOperand, double secondOperand, String operator) {
        IBinaryOperation<Double, Double> operation;
        switch (operator) {
            case "%":
                operation = new ModuloDoubleOperation();
                break;
            case "+":
                operation = new AddDoubleOperation();
                break;
            case "-":
                operation = new SubtractDoubleOperation();
                break;
            case "*":
                operation = new MultiplyDoubleOperation();
                break;
            default:
                throw new RuntimeException("Method not implemented!");
        }

        return new Answer<>(operation.apply(firstOperand, secondOperand));
    }
}
