package me.dort.calc.ast;

public interface Expression {

    <T> T accept(ExpressionVisitor<T> visitor);
}
