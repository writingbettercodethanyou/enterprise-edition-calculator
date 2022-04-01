package me.dort.calc.classes.operation.add;

import me.dort.calc.interfaces.operation.add.IAdd;

public class AddImpl implements IAdd<Double> {
    @Override
    public Double doOperation(Double arg0, Double arg1) {
        return arg0 + arg1;
    }
}
