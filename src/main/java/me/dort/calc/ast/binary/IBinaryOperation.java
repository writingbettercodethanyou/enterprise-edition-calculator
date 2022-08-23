package me.dort.calc.ast.binary;

import java.util.function.BiFunction;

public interface IBinaryOperation<T, R> extends BiFunction<T, T, R> {
}
