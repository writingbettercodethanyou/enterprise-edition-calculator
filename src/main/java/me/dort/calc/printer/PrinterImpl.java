package me.dort.calc.printer;

import me.dort.calc.printer.text.creator.IStringFromString;

public class PrinterImpl implements IPrinter {
    @Override
    public void println(String string) {
        System.out.println(IStringFromString.createStringFromText(string).text());
    }
}
