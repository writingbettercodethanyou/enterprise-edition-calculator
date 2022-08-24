package me.dort.calc;

import me.dort.calc.ast.Expression;
import me.dort.calc.ast.Parser;
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

            Expression expression = Parser.parse(read);
            System.out.print(expression);
            System.out.print(" = ");
            System.out.println(expression.evaluate());
        }
    }
}
