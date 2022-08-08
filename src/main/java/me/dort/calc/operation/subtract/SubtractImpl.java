package me.dort.calc.operation.subtract;

public class SubtractImpl implements ISubtract<Double> {

    @Override
    public Double doOperation(Double arg0, Double arg1) {
        return arg0 - arg1;
    }
}
