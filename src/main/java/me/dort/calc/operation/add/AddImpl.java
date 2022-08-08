package me.dort.calc.operation.add;

public class AddImpl implements IAdd<Double> {
    @Override
    public Double doOperation(Double arg0, Double arg1) {
        return arg0 + arg1;
    }
}
