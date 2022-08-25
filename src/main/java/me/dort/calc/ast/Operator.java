package me.dort.calc.ast;

import me.dort.calc.ast.lexer.SpecialCharacter;

public enum Operator {

    ADD(SpecialCharacter.ADD, 2),
    SUBTRACT(SpecialCharacter.SUBTRACT, 2),
    MULTIPLY(SpecialCharacter.MULTIPLY, 1),
    DIVIDE(SpecialCharacter.DIVIDE, 1),
    MODULO(SpecialCharacter.MODULO, 1),
    EXPONENT(SpecialCharacter.EXPONENT, 0);

    private final SpecialCharacter character;
    private final int ordinance;

    Operator(SpecialCharacter character, int ordinance) {
        this.character = character;
        this.ordinance = ordinance;
    }

    public int getOrdinance() {
        return ordinance;
    }

    @Override
    public String toString() {
        return character.toString();
    }
}
