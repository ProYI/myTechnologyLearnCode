package org.example.netty.lambda;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DishTest {
    public static void main(String[] args) {

        IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a*a + b*b)})
                        .filter(t -> t[2]%1==0)
        ).forEach(e -> System.out.println(Arrays.toString(e)));
    }
}
