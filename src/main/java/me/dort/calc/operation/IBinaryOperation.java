package me.dort.calc.operation;

import java.util.function.BiFunction;

public interface IBinaryOperation<T, R> extends BiFunction<T, T, R> {
}
