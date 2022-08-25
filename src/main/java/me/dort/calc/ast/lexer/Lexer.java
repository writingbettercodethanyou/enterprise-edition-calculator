package me.dort.calc.ast.lexer;

import me.dort.calc.ast.Reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Lexer extends Reader<Character> {

    public static Token[] lex(String input) throws IOException {
        Lexer       lexer  = new Lexer(input.chars().mapToObj(c -> (char) c).toArray(Character[]::new));
        List<Token> tokens = new ArrayList<>();

        Token token;
        while ((token = lexer.readToken()) != null)
            tokens.add(token);

        return tokens.toArray(Token[]::new);
    }

    private Lexer(Character[] characters) {
        super(characters);
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
            case '+', '-', '*', '/', '%', '^', '(', ')' -> readSpecialToken();
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

    private SpecialToken readSpecialToken() throws IOException {
        try {
            SpecialCharacter character = switch (peek()) {
                case '+' -> SpecialCharacter.ADD;
                case '-' -> SpecialCharacter.SUBTRACT;
                case '*' -> SpecialCharacter.MULTIPLY;
                case '/' -> SpecialCharacter.DIVIDE;
                case '%' -> SpecialCharacter.MODULO;
                case '^' -> SpecialCharacter.EXPONENT;
                case '(' -> SpecialCharacter.GROUP_START;
                case ')' -> SpecialCharacter.GROUP_END;
                default -> throw new IOException("expected special character token at position [" + getPosition() + "]");
            };

            return new SpecialToken(character.toString(), getPosition(), character);
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
