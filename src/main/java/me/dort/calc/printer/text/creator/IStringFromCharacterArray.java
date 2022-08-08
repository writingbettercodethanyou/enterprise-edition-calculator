package me.dort.calc.printer.text.creator;

import me.dort.calc.printer.text.string.IString;

public interface IStringFromCharacterArray extends IStringFromCharacter {
    static IString createString(char[] characters) {
        return IStringFromString.createStringFromText(new String(characters));
    }
}
