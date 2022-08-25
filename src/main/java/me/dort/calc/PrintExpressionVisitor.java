package me.dort.calc;

import me.dort.calc.ast.ConstantDoubleExpression;
import me.dort.calc.ast.ExpressionVisitor;
import me.dort.calc.ast.BinaryExpression;

public class PrintExpressionVisitor implements ExpressionVisitor<String> {

    @Override
    public String visitBinaryExpression(BinaryExpression expression) {
        return expression.getFirstOperand().accept(this) + " " + expression.getOperator() + " " + expression.getSecondOperand().accept(this);
    }

    @Override
    public String visitConstantDoubleExpression(ConstantDoubleExpression expression) {
        return expression.getValue().toString();
    }
}
