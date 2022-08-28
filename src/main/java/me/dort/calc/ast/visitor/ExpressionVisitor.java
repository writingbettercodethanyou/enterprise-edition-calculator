package me.dort.calc.ast.visitor;

import me.dort.calc.ast.BinaryExpression;
import me.dort.calc.ast.ConstantDoubleExpression;
import me.dort.calc.ast.GroupedExpression;

public interface ExpressionVisitor<T> {

    T visitBinaryExpression(BinaryExpression expression);

    T visitConstantDoubleExpression(ConstantDoubleExpression expression);

    T visitGroupedExpression(GroupedExpression expression);
}
