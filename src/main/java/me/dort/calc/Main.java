package me.dort.calc;

import me.dort.calc.ast.Expression;
import me.dort.calc.ast.Parser;
import me.dort.calc.ast.visitor.EvaluateExpressionVisitor;
import me.dort.calc.ast.visitor.PrintExpressionVisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String read = reader.readLine();
            if (read == null)
                break;

            Expression expression = Parser.parse(read);
            System.out.print(expression.accept(new PrintExpressionVisitor()));
            System.out.print(" = ");
            System.out.println(expression.accept(new EvaluateExpressionVisitor()));
        }
    }
}
