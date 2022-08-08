package me.dort.calc.classes.operation.modulo;

import me.dort.calc.interfaces.operation.modulo.IModulo;

public class ModuloImpl implements IModulo<Double> {
    @Override
    public Double doOperation(Double arg0, Double arg1) {
        return arg0 % arg1;
    }
}
