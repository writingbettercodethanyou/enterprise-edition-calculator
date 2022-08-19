package me.dort.calc.lexer;

import java.io.EOFException;
import java.io.IOException;
import java.util.List;

public final class Lexer {

    public static Iterable<Token> lex(String input) throws IOException {
        return new Lexer(input).lex();
    }

    private final char[] chars;

    private int position;

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
        return chars[position = Math.min(position + characters + 1, chars.length - 1)];
    }

    public Iterable<Token> lex() throws IOException {
        return List.of();
    }
}
