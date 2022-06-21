package org.example.netty.lambda.collect;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectTest {
    public static void main(String[] args) {
        List<Integer> list = ImmutableList.of(1, 2, 3, 4, 5, 6);

        long a = list.stream().count();
        System.out.println("a : " + a);
        System.out.println("--------------------------");

        long b = list.stream().collect(Collectors.counting());
        System.out.println("b : " + b);
        System.out.println("--------------------------");

        Optional<Integer> maxOpt1 = list.stream().collect(Collectors.maxBy(Comparator.comparingInt(Integer::intValue)));
        Integer max1 = maxOpt1.orElse(null);
        System.out.println("max1 : " + max1);
        System.out.println("--------------------------");

        Optional<Integer> maxOpt2 = list.stream().max(Comparator.comparingInt(Integer::intValue));
        Integer max2 = maxOpt2.orElse(null);
        System.out.println("max2 : " + max2);
        System.out.println("--------------------------");

        Integer sum1 = list.stream().collect(Collectors.summingInt(Integer::intValue));
        System.out.println("sum1 : " + sum1);
        System.out.println("--------------------------");

        Integer sum2 = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println("sum2 : " + sum2);
        System.out.println("--------------------------");

        Double avg = list.stream().collect(Collectors.averagingInt(Integer::intValue));
        System.out.println("avg : " + avg);
        System.out.println("--------------------------");

        /**
         * 一次性拿到最值、平均数、求和的对象
         */
        IntSummaryStatistics statistics = list.stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("statistics.max : " + statistics.getMax());
        System.out.println("statistics.min : " + statistics.getMin());
        System.out.println("statistics.avg : " + statistics.getAverage());
        System.out.println("statistics.count : " + statistics.getCount());
        System.out.println("statistics.sum : " + statistics.getSum());
        System.out.println("--------------------------");

        /**
         * 连接字符串
         */
        String str = list.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println("str: " + str);
        System.out.println("--------------------------");

        /**
         * 归约
         */
        int reducing1 = list.stream().collect(Collectors.reducing(0, (i, j) -> i + j));
        System.out.println("reducing1: " + reducing1);
        System.out.println("--------------------------");

        int reducing2 = list.stream().reduce(0, Integer::sum);
        System.out.println("reducing2: " + reducing2);
        System.out.println("--------------------------");

    }
}
