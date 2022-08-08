package me.dort.calc.operation;

import java.util.function.BiFunction;

public interface IOperation<T> extends BiFunction<T, T, T> {
}
