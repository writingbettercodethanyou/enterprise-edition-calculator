package me.dort.calc.calculator;

import me.dort.calc.answer.Answer;
import me.dort.calc.operation.AddDoubleOperation;
import me.dort.calc.operation.ModuloDoubleOperation;
import me.dort.calc.operation.MultiplyDoubleOperation;
import me.dort.calc.operation.SubtractDoubleOperation;
import me.dort.calc.operation.IOperation;

public class Calculator implements ICalculator {

    @Override
    public Answer<Double> performCalculation(double firstOperand, double secondOperand, String operator) {
        IOperation<Double> operation;
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
