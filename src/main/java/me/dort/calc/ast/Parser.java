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
        while (canPeek() && !(grouped && peek() instanceof SpecialToken && ((SpecialToken) peek()).getCharacter() == SpecialCharacter.GROUP_END)) {

            SpecialToken specialToken = (SpecialToken) next();
            Operator      actualOperator;
            Expression    secondOperand;
            switch (specialToken.getCharacter()) {
                case GROUP_START -> {
                    actualOperator = Operator.MULTIPLY;
                    secondOperand = readExpression(true);
                }
                case GROUP_END -> throw new IOException("unexpected GROUP_END token at token position [" + getPosition() + "]");
                case ADD -> {
                    actualOperator = Operator.ADD;
                    secondOperand = readOperand();
                }
                case SUBTRACT -> {
                    actualOperator = Operator.SUBTRACT;
                    secondOperand = readOperand();
                }
                case MULTIPLY -> {
                    actualOperator = Operator.MULTIPLY;
                    secondOperand = readOperand();
                }
                case DIVIDE -> {
                    actualOperator = Operator.DIVIDE;
                    secondOperand = readOperand();
                }
                case MODULO -> {
                    actualOperator = Operator.MODULO;
                    secondOperand = readOperand();
                }
                case EXPONENT -> {
                    actualOperator = Operator.EXPONENT;
                    secondOperand = readOperand();
                }
                default -> throw new UnsupportedOperationException("unsupported special character at token position [" + getPosition() + "]");
            }

            BinaryExpression expression = switch (actualOperator) {
                case ADD -> new AddExpression(null, secondOperand);
                case SUBTRACT -> new SubtractExpression(null, secondOperand);
                case MULTIPLY -> new MultiplyExpression(null, secondOperand);
                case DIVIDE -> new DivideExpression(null, secondOperand);
                case MODULO -> new ModuloExpression(null, secondOperand);
                case EXPONENT -> new ExponentExpression(null, secondOperand);
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

        if (grouped && !(canPeek() && peek() instanceof SpecialToken && ((SpecialToken) next()).getCharacter() == SpecialCharacter.GROUP_END))
            throw new IOException("expected GROUP_END token, but got nothing at token position [" + getPosition() + "]");

        return root;
    }

    private Expression readOperand() throws IOException {
        if (!canPeek())
            throw new IOException("expected short expression, but got nothing at token position [" + getPosition() + "]");

        Token token = next();

        if (token instanceof SpecialToken) {
            if (((SpecialToken) token).getCharacter() == SpecialCharacter.GROUP_START)
                return readExpression(true);

            throw new IOException("a short expression must only be a numeric token or a grouped expression");
        } else if (token instanceof NumberToken)
            return new ConstantExpression(((NumberToken) token).getValue());

        throw new UnsupportedOperationException("token type not supported yet");
    }
}
