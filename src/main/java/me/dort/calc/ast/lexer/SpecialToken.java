package me.dort.calc.ast.lexer;

public final class SpecialToken extends Token {

    private final SpecialCharacter character;

    public SpecialToken(String literalString, int location, SpecialCharacter character) {
        super(literalString, location);
        this.character = character;
    }

    public SpecialCharacter getCharacter() {
        return character;
    }
}
