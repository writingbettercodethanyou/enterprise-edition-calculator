package me.dort.calc.interfaces.printer.interfaces.text.creator;

import me.dort.calc.interfaces.printer.interfaces.text.string.IString;

public interface IStringFromString {

    static IString createStringFromText(CharSequence text) {
        return text::toString;
    }

}
