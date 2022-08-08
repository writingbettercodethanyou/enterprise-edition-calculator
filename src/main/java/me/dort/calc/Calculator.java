package me.dort.calc;

import me.dort.calc.answer.Answer;
import me.dort.calc.operation.AddDoubleOperation;
import me.dort.calc.operation.ModuloDoubleOperation;
import me.dort.calc.operation.MultiplyDoubleOperation;
import me.dort.calc.operation.SubtractDoubleOperation;
import me.dort.calc.calculator.ICalculator;
import me.dort.calc.operation.IOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculator implements ICalculator  {
    public Calculator(String... args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String read = reader.readLine();
            if (read != null) {
                String[] split = read.split(" ");
                String first = split[0];
                String operatorString = split[1];
                String second = split[2];
                IOperation<Double> operation;
                switch (operatorString) {
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
                Answer<Double> answer = new Answer<>(operation.apply(Double.parseDouble(first), Double.parseDouble(second)));
                System.out.println(first + " " + " " + operation + " " + second + " " + "=" + " " + answer.getAnswer());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Calculator(args);
    }
}
