package me.dort.calc.ast.visitor;

import me.dort.calc.ast.ConstantDoubleExpression;
import me.dort.calc.ast.BinaryExpression;
import me.dort.calc.ast.GroupedExpression;

public class EvaluateExpressionVisitor implements ExpressionVisitor<Double> {

    @Override
    public Double visitBinaryExpression(BinaryExpression expression) {
        double firstOperand  = expression.getFirstOperand().accept(this),
               secondOperand = expression.getSecondOperand().accept(this);

        return switch (expression.getOperator()) {
            case ADD -> firstOperand + secondOperand;
            case SUBTRACT -> firstOperand - secondOperand;
            case MULTIPLY -> firstOperand * secondOperand;
            case DIVIDE -> firstOperand / secondOperand;
            case MODULO -> firstOperand % secondOperand;
            case EXPONENT -> Math.pow(firstOperand, secondOperand);
        };
    }

    @Override
    public Double visitConstantDoubleExpression(ConstantDoubleExpression expression) {
        return expression.getValue();
    }

    @Override
    public Double visitGroupedExpression(GroupedExpression expression) {
        return expression.getExpression().accept(this);
    }
}
