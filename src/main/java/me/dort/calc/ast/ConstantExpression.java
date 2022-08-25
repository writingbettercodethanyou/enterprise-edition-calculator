package me.dort.calc.ast;

public abstract class ConstantExpression<T> implements Expression {

    private final T value;

    public ConstantExpression(T number) {
        this.value = number;
    }

    public T getValue() {
        return value;
    }
}
