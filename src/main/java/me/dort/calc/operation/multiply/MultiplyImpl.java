package me.dort.calc.operation.multiply;

public class MultiplyImpl implements IMultiply<Double> {
    @Override
    public Double doOperation(Double arg0, Double arg1) {
        return arg0 * arg1;
    }
}
