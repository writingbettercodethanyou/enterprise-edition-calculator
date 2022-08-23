package me.dort.calc.ast.binary;

public class ModuloDoubleOperation implements IHomogeneousBinaryOperation<Double> {

    @Override
    public Double apply(Double arg0, Double arg1) {
        return arg0 % arg1;
    }
}