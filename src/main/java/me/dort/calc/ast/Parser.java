package me.dort.calc.ast;

import me.dort.calc.ast.binary.*;
import me.dort.calc.ast.lexer.*;

import java.io.IOException;

public class Parser extends Reader<Token> {

    public static Expression parse(String input) throws IOException {
        return new Parser(Lexer.lex(input)).readExpression(false);
    }

    private Parser(Token[] tokens) {
        super(tokens);
    }

    private Expression readExpression(boolean grouped) throws IOException {

        Expression root = readOperand();
        while (canPeek() && !(grouped && peek() instanceof OperatorToken && ((OperatorToken) peek()).getOperation() == Operator.GROUP_END)) {

            OperatorToken operatorToken = (OperatorToken) next();
            Operator      actualOperator;
            Expression    secondOperand;
            switch (operatorToken.getOperation()) {
                case GROUP_START -> {
                    actualOperator = Operator.MULTIPLY;
                    secondOperand = readExpression(true);
                }
                case GROUP_END -> throw new IOException("unexpected GROUP_END token at token position [" + getPosition() + "]");
                default -> {
                    actualOperator = operatorToken.getOperation();
                    secondOperand = readOperand();
                }
            }

            BinaryExpression expression = switch (actualOperator) {
                case ADD -> new AddExpression(null, secondOperand);
                case SUBTRACT -> new SubtractExpression(null, secondOperand);
                case MULTIPLY -> new MultiplyExpression(null, secondOperand);
                case DIVIDE -> new DivideExpression(null, secondOperand);
                case MODULO -> new ModuloExpression(null, secondOperand);
                case EXPONENT -> new ExponentExpression(null, secondOperand);
                default -> throw new UnsupportedOperationException("operator not supported yet");
            };

            Expression parent = null, node = root;
            while (node instanceof BinaryExpression && ((BinaryExpression) node).getOperator().getOrdinance() > actualOperator.getOrdinance())  {
                parent = node;
                node = ((BinaryExpression) node).getSecondOperand();
            }

            if (parent == null)
                root = expression;
            else
                ((BinaryExpression) parent).setSecondOperand(expression);

            expression.setFirstOperand(node);
        }

        if (grouped && !(canPeek() && peek() instanceof OperatorToken && ((OperatorToken) next()).getOperation() == Operator.GROUP_END))
            throw new IOException("expected GROUP_END token, but got nothing at token position [" + getPosition() + "]");

        return root;
    }

    private Expression readOperand() throws IOException {
        if (!canPeek())
            throw new IOException("expected short expression, but got nothing at token position [" + getPosition() + "]");

        Token token = next();

        if (token instanceof OperatorToken) {
            if (((OperatorToken) token).getOperation() == Operator.GROUP_START)
                return readExpression(true);

            throw new IOException("a short expression must only be a numeric token or a grouped expression");
        } else if (token instanceof NumberToken)
            return new ConstantExpression(((NumberToken) token).getValue());

        throw new UnsupportedOperationException("token type not supported yet");
    }
}
