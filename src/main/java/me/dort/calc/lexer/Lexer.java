package me.dort.calc.lexer;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Lexer {

    public static Iterable<Token> lex(String input) throws IOException {
        return new Lexer(input).lex();
    }

    private final char[] chars;

    private int position = -1;

    private Lexer(String input) {
        this.chars = input.toCharArray();
    }

    private char current() {
        return chars[position];
    }

    private char next() throws EOFException {
        if (chars.length <= position + 1)
            throw new EOFException("no further characters");
        return chars[++position];
    }

    private boolean canPeek() {
        return canPeek(1);
    }

    private boolean canPeek(int lookAhead) {
        return !(lookAhead < 0 || chars.length <= position + lookAhead);
    }

    private char peek() throws EOFException {
        return peek(1);
    }

    private char peek(int lookAhead) throws EOFException {
        if (lookAhead < 0)
            throw new IllegalArgumentException("negative [lookAhead] supplied");
        if (chars.length <= position + lookAhead)
            throw new EOFException("no further characters; tried to read index [" + (position + lookAhead) + "] for length [" + chars.length + "]");
        return chars[position + lookAhead];
    }

    private char skip() {
        return skip(1);
    }

    private char skip(int characters) {
        return chars[position = Math.min(position + characters, chars.length - 1)];
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
            default -> throw new IOException("unreadable token at position [" + (position + 1) + "]");
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
                throw new IOException("expected numeric character after '.' in numeric token at position [" + (position + 1) + "]");

            while (canPeek() && isNumeric(peek()))
                builder.append(next());
        }

        String s = builder.toString();
        return new NumberToken(s, position + 1 - s.length(), Double.parseDouble(s));
    }

    private OperatorToken readOperatorToken() throws IOException {
        Operator operator = switch (next()) {
            case '+' -> Operator.ADD;
            case '-' -> Operator.SUBTRACT;
            case '*' -> Operator.MULTIPLY;
            case '/' -> Operator.DIVIDE;
            case '%' -> Operator.MODULO;
            case '^' -> Operator.EXPONENT;
            case '(' -> Operator.GROUP_START;
            case ')' -> Operator.GROUP_END;
            default -> throw new IOException("expected operator character in operator token at position [" + position + "]");
        };

        return new OperatorToken(operator.toString(), position, operator);
    }

    private static boolean isNumeric(char c) {
        return switch (c) {
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> true;
            default -> false;
        };
    }
}
