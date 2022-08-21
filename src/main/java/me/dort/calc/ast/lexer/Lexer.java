package me.dort.calc.ast.lexer;

import me.dort.calc.ast.Reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Lexer extends Reader<Character> {

    public static Iterable<Token> lex(String input) throws IOException {
        return new Lexer(input).lex();
    }

    private Lexer(String input) {
        super(input.chars().mapToObj(c -> (char) c).toArray(Character[]::new));
    }

    private Iterable<Token> lex() throws IOException {
        List<Token> tokens = new ArrayList<>();

        Token token;
        while ((token = readToken()) != null)
            tokens.add(token);

        return tokens;
    }

    private Token readToken() throws IOException {
        // ignore whitespace
        while (canPeek() && Character.isWhitespace(peek()))
            skip();

        // when at eof return null
        if (!canPeek())
            return null;

        return switch (peek()) {
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> readNumberToken();
            case '+', '-', '*', '/', '%', '^', '(', ')' -> readOperatorToken();
            default -> throw new IOException("unreadable token at position [" + getPosition() + "]");
        };
    }

    private NumberToken readNumberToken() throws IOException {
        StringBuilder builder = new StringBuilder();
        while (canPeek() && isNumeric(peek()))
            builder.append(next());

        if (canPeek() && peek() == '.') {
            skip();
            builder.append('.');

            if (!(canPeek() && isNumeric(peek())))
                throw new IOException("expected numeric character after '.' in numeric token at position [" + getPosition() + "]");

            while (canPeek() && isNumeric(peek()))
                builder.append(next());
        }

        String s = builder.toString();
        return new NumberToken(s, getPosition() - s.length(), Double.parseDouble(s));
    }

    private OperatorToken readOperatorToken() throws IOException {
        try {
            Operator operator = switch (peek()) {
                case '+' -> Operator.ADD;
                case '-' -> Operator.SUBTRACT;
                case '*' -> Operator.MULTIPLY;
                case '/' -> Operator.DIVIDE;
                case '%' -> Operator.MODULO;
                case '^' -> Operator.EXPONENT;
                case '(' -> Operator.GROUP_START;
                case ')' -> Operator.GROUP_END;
                default -> throw new IOException("expected operator character in operator token at position [" + getPosition() + "]");
            };

            return new OperatorToken(operator.toString(), getPosition(), operator);
        } finally {
            skip();
        }
    }

    private static boolean isNumeric(char c) {
        return switch (c) {
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> true;
            default -> false;
        };
    }
}
