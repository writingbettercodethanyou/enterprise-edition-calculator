package me.dort.calc.ast;

import me.dort.calc.ast.visitor.ExpressionVisitor;

public class ConstantDoubleExpression extends ConstantExpression<Double> {

    public ConstantDoubleExpression(double value) {
        super(value);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitConstantDoubleExpression(this);
    }
}
