package me.dort.calc.ast;

public class GroupedExpression implements Expression {

    private final Expression expression;

    public GroupedExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitGroupedExpression(this);
    }

    public Expression getExpression() {
        return expression;
    }
}
