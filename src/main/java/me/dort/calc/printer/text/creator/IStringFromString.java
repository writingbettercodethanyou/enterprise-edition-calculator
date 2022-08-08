package me.dort.calc.printer.text.creator;

import me.dort.calc.printer.text.string.IString;

public interface IStringFromString {

    static IString createStringFromText(CharSequence text) {
        return text::toString;
    }

}
