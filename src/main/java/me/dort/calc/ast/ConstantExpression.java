package me.dort.calc.ast;

public class ConstantExpression implements Expression {

    private final double number;

    public ConstantExpression(double number) {
        this.number = number;
    }

    @Override
    public double evaluate() {
        return number;
    }

    @Override
    public String toString() {
        return Double.toString(number);
    }
}
