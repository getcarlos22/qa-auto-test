package com.github.utils.function;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class Memoize {

    public static <T> Supplier<T> memoizeSuppliers(final Supplier<T> s) {
        final Map<Long, T> lazy = new ConcurrentHashMap<>();
        return () -> lazy.computeIfAbsent(1L, i -> s.get());
    }
}
