package me.dort.calc.classes.operation.subtract;

import me.dort.calc.interfaces.operation.subtract.ISubtract;

public class SubtractImpl implements ISubtract<Double> {

    @Override
    public Double doOperation(Double arg0, Double arg1) {
        return arg0 - arg1;
    }
}
