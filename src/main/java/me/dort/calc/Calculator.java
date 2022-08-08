package me.dort.calc;

import me.dort.calc.answer.AnswerImpl;
import me.dort.calc.operation.add.AddImpl;
import me.dort.calc.operation.modulo.ModuloImpl;
import me.dort.calc.operation.multiply.MultiplyImpl;
import me.dort.calc.operation.subtract.SubtractImpl;
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
                String operation = split[1];
                String second = split[2];
                IOperation<Double> ioperation;
                switch (operation) {
                    case "%":
                        ioperation = new ModuloImpl();
                        break;
                    case "+":
                        ioperation = new AddImpl();
                        break;
                    case "-":
                        ioperation = new SubtractImpl();
                        break;
                    case "*":
                        ioperation = new MultiplyImpl();
                        break;
                    default:
                        throw new RuntimeException("Method not implemented!");
                }
                System.out.println(first + " " + " " + operation + " " + second + " " + "=" + " " + new AnswerImpl(ioperation.doOperation(Double.parseDouble(first), Double.parseDouble(second))).answer());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Calculator(args);
    }
}
