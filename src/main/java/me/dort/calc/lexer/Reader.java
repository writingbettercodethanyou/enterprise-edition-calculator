package me.dort.calc.lexer;

import java.io.EOFException;

public abstract class Reader<T> {

    private final T[] chars;

    private int position = -1;

    protected Reader(T[] input) {
        this.chars = input;
    }

    protected T current() {
        return chars[position];
    }

    protected T next() throws EOFException {
        if (chars.length <= position + 1)
            throw new EOFException("no further characters");
        return chars[++position];
    }

    protected boolean canPeek() {
        return canPeek(1);
    }

    protected boolean canPeek(int lookAhead) {
        return !(lookAhead < 0 || chars.length <= position + lookAhead);
    }

    protected T peek() throws EOFException {
        return peek(1);
    }

    protected T peek(int lookAhead) throws EOFException {
        if (lookAhead < 0)
            throw new IllegalArgumentException("negative [lookAhead] supplied");
        if (chars.length <= position + lookAhead)
            throw new EOFException("no further characters; tried to read index [" + (position + lookAhead) + "] for length [" + chars.length + "]");
        return chars[position + lookAhead];
    }

    protected T skip() {
        return skip(1);
    }

    protected T skip(int characters) {
        return chars[position = Math.min(position + characters, chars.length - 1)];
    }

    protected int getPosition() {
        return position + 1;
    }
}
