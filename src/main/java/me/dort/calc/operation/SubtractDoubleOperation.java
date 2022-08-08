package me.dort.calc.operation;

public class SubtractDoubleOperation implements IOperation<Double> {

    @Override
    public Double apply(Double arg0, Double arg1) {
        return arg0 - arg1;
    }
}
