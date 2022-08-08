package me.dort.calc.printer;

public class PrinterImpl implements IPrinter {

    @Override
    public void println(String string) {
        System.out.println(string);
    }
}
