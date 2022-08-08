package me.dort.calc.printer.text.creator;

import me.dort.calc.printer.text.string.IString;

public interface IStringFromCharacter extends IString {

    static IString createString(Character character) {
        return IStringFromString.createStringFromText(String.valueOf(character.charValue()));
    }

}
