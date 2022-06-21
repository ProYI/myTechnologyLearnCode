package org.example.netty.lambda;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
