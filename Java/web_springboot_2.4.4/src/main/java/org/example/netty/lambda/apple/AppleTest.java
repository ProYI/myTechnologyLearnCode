package org.example.netty.lambda.apple;


import org.example.netty.lambda.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppleTest<T> {
    public static List<Apple> filterApples(List<Apple> inventory,
                                           ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    // 使用行为参数化优化代码
    public static void main(String[] args) {
        List<Apple> inverntory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        // 匿名类
//        List<Apple> heavyApples = filterApples(inverntory, new ApplePredicate() {
//            @Override
//            public boolean test(Apple apple) {
//                return apple.getWeight()>150;
//            }
//        });


        // lambda
        List<Apple> heavyApples = filterApples(inverntory, (Apple apple) -> apple.getWeight()>150);
    }

    // 将filterApples抽象成通用方法
    public List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

}
