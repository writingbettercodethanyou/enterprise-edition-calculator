package me.dort.calc.ast;

public interface ExpressionVisitor<T> {

    T visitBinaryExpression(BinaryExpression expression);

    T visitConstantDoubleExpression(ConstantDoubleExpression expression);

    T visitGroupedExpression(GroupedExpression expression);
}
