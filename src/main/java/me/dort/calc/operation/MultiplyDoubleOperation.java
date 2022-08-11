package me.dort.calc.operation;

public class MultiplyDoubleOperation implements IHomogeneousBinaryOperation<Double> {

    @Override
    public Double apply(Double arg0, Double arg1) {
        return arg0 * arg1;
    }
}
