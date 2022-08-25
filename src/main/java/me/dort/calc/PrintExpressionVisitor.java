package me.dort.calc;

import me.dort.calc.ast.ConstantDoubleExpression;
import me.dort.calc.ast.ExpressionVisitor;
import me.dort.calc.ast.BinaryExpression;
import me.dort.calc.ast.GroupedExpression;

public class PrintExpressionVisitor implements ExpressionVisitor<String> {

    private final boolean explicitGrouping;

    public PrintExpressionVisitor() {
        this(false);
    }

    public PrintExpressionVisitor(boolean explicitGrouping) {
        this.explicitGrouping = explicitGrouping;
    }

    @Override
    public String visitBinaryExpression(BinaryExpression expression) {
        return (explicitGrouping ? "(" : "") + expression.getFirstOperand().accept(this) + " " + expression.getOperator() + " " + expression.getSecondOperand().accept(this) + (explicitGrouping ? ")" : "");
    }

    @Override
    public String visitConstantDoubleExpression(ConstantDoubleExpression expression) {
        return expression.getValue().toString();
    }

    @Override
    public String visitGroupedExpression(GroupedExpression expression) {
        return "(" + expression.getExpression().accept(this) + ")";
    }
}
