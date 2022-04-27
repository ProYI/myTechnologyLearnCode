package org.example.netty.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestPredicate {
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T s : list) {
            if (p.test(s)) {
                result.add(s);
            }
        }
        return result;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        list.forEach(c::accept);
    }

    public static <T,R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s :
                list) {
            result.add(f.apply(s));
        };
        return result;
    }

    public static void main(String[] args) {
//        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
//        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);

        forEach(Arrays.asList(1,2,3,4,5), System.out::println);

        List<Integer> lengthResult =  map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());
        System.out.println(lengthResult.toString());
    }
}
