package org.example.netty.lambda;

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
