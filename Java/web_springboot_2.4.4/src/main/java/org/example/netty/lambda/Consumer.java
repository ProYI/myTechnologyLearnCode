package org.example.netty.lambda;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
