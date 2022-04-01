package me.dort.calc.interfaces.printer.interfaces.text.creator;

import me.dort.calc.interfaces.printer.interfaces.text.string.IString;

public interface IStringFromCharacter extends IString {

    static IString createString(Character character) {
        return IStringFromString.createStringFromText(String.valueOf(character.charValue()));
    }

}
