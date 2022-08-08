package me.dort.calc.classes.operation.multiply;

import me.dort.calc.interfaces.operation.multiply.IMultiply;

public class MultiplyImpl implements IMultiply<Double> {
    @Override
    public Double doOperation(Double arg0, Double arg1) {
        return arg0 * arg1;
    }
}
