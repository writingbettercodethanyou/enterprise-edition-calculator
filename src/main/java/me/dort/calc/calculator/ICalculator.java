package me.dort.calc.calculator;

import me.dort.calc.answer.Answer;

public interface ICalculator {

    Answer<Double> performCalculation(double firstOperand, double secondOperand, String operator);
}
