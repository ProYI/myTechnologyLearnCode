package org.example.netty.lambda.collector;

import org.example.netty.lambda.fenqu.FenQu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 质数收集器
 */
public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {
            {
                put(true, new ArrayList<Integer>());
                put(false, new ArrayList<Integer>());
            }
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(isPrime(candidate)).add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.range(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            Map<Boolean, List<Integer>> result = IntStream.rangeClosed(2, 1_000_000).boxed().collect(new PrimeNumbersCollector());
            System.out.println("质数个数: " + result.get(true).size());
            System.out.println("非质数个数: " + result.get(false).size());

            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println("自定义 Fastest execution done in " + fastest + " msecs");
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            Map<Boolean, List<Integer>> result = IntStream.rangeClosed(2, 1_000_000).boxed().collect(Collectors.partitioningBy(FenQu::isPrime));
            System.out.println("质数个数: " + result.get(true).size());
            System.out.println("非质数个数: " + result.get(false).size());

            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println("JDK Fastest execution done in " + fastest + " msecs");

        // 自定义性能不如partitioningBy 😂😂😂
    }
}
