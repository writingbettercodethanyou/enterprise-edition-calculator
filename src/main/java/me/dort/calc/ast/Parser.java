package me.dort.calc.ast;

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

        Expression operand = readOperand();
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

            Expression firstOperand = operand;
            operand = switch (actualOperator) {
                case ADD -> () -> firstOperand.evaluate() + secondOperand.evaluate();
                case SUBTRACT -> () -> firstOperand.evaluate() - secondOperand.evaluate();
                case MULTIPLY -> () -> firstOperand.evaluate() * secondOperand.evaluate();
                case DIVIDE -> () -> firstOperand.evaluate() / secondOperand.evaluate();
                case MODULO -> () -> firstOperand.evaluate() % secondOperand.evaluate();
                case EXPONENT -> () -> Math.pow(firstOperand.evaluate(), secondOperand.evaluate());
                default -> throw new UnsupportedOperationException("operator not supported yet");
            };
        }

        if (grouped && !(canPeek() && peek() instanceof OperatorToken && ((OperatorToken) next()).getOperation() == Operator.GROUP_END))
            throw new IOException("expected GROUP_END token, but got nothing at token position [" + getPosition() + "]");

        return operand;
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
            return () -> ((NumberToken) token).getValue();

        throw new UnsupportedOperationException("token type not supported yet");
    }
}
