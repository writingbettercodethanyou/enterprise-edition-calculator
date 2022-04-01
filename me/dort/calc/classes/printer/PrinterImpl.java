package me.dort.calc.classes.printer;

import me.dort.calc.interfaces.printer.IPrinter;
import me.dort.calc.interfaces.printer.interfaces.text.creator.IStringFromString;

public class PrinterImpl implements IPrinter {
    @Override
    public void println(String string) {
        System.out.println(IStringFromString.createStringFromText(string).text());
    }
}
