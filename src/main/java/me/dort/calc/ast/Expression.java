package me.dort.calc.ast;

import me.dort.calc.ast.visitor.ExpressionVisitor;

public interface Expression {

    <T> T accept(ExpressionVisitor<T> visitor);
}
