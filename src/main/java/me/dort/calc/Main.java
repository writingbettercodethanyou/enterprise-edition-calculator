package me.dort.calc;

import me.dort.calc.answer.Answer;
import me.dort.calc.calculator.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Calculator calculator = new Calculator();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String read = reader.readLine();
            if (read == null)
                break;

            String[] split = read.split(" ");

            String firstOperand  = split[0];
            String secondOperand = split[2];
            String operator      = split[1];

            Answer<Double> answer = calculator.performCalculation(Double.parseDouble(firstOperand), Double.parseDouble(secondOperand), operator);
            System.out.println(firstOperand + " " + operator + " " + secondOperand + " = " + answer.getAnswer());
        }
    }
}
