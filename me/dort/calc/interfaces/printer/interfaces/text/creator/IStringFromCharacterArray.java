package me.dort.calc.interfaces.printer.interfaces.text.creator;

import me.dort.calc.interfaces.printer.interfaces.text.string.IString;

public interface IStringFromCharacterArray extends IStringFromCharacter {
    static IString createString(char[] characters) {
        return IStringFromString.createStringFromText(new String(characters));
    }
}
