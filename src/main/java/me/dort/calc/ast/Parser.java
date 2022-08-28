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

        Expression root = readOperand();
        while (canPeek() && !(grouped && peek() instanceof SpecialToken && ((SpecialToken) peek()).getCharacter() == SpecialCharacter.GROUP_END)) {

            SpecialToken specialToken   = (SpecialToken) next();
            BinaryOperator actualOperator = switch (specialToken.getCharacter()) {
                case MULTIPLY, GROUP_START -> BinaryOperator.MULTIPLY;
                case ADD -> BinaryOperator.ADD;
                case SUBTRACT -> BinaryOperator.SUBTRACT;
                case DIVIDE -> BinaryOperator.DIVIDE;
                case MODULO -> BinaryOperator.MODULO;
                case EXPONENT -> BinaryOperator.EXPONENT;
                case GROUP_END -> throw new IOException("unexpected GROUP_END token at token position [" + getPosition() + "]");
            };

            Expression secondOperand = specialToken.getCharacter() == SpecialCharacter.GROUP_START
                    ? readExpression(true)
                    : readOperand();

            BinaryExpression parent = null;
            Expression       node   = root;
            while (node instanceof BinaryExpression nodeAsBinaryExpr && nodeAsBinaryExpr.getOperator().getOrdinance() > actualOperator.getOrdinance())  {
                parent = nodeAsBinaryExpr;
                node = nodeAsBinaryExpr.getSecondOperand();
            }

            BinaryExpression expression = new BinaryExpression(actualOperator, node, secondOperand);

            if (parent == null)
                root = expression;
            else
                parent.setSecondOperand(expression);
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
                return new GroupedExpression(readExpression(true));

            throw new IOException("a short expression must only be a numeric token or a grouped expression");
        } else if (token instanceof NumberToken)
            return new ConstantDoubleExpression(((NumberToken) token).getValue());

        throw new UnsupportedOperationException("token type not supported yet");
    }
}
