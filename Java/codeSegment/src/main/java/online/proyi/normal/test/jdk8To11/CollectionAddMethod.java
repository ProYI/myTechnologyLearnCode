package online.proyi.normal.test.jdk8To11;

import cn.hutool.json.JSONUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 集合 及 流 增加方法
 */
public class CollectionAddMethod {
    public static void main(String[] args) {
        // 生成集合对象为不可变对象
        List<String> list = List.of("this", "is", "immutable", "list");
//        list.add("test"); UnsupportedOperationException 不可变导致add失败
        Set<String> set = Set.of("this", "is", "immutable", "set");
        Map<String, String> map = Map.of("this", "is", "immutable", "map");

        Map<String, String> mapEntry = Map.ofEntries(Map.entry("this", "is"), Map.entry("immutable", "map"));

        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        // Stream增加方法 JDK9之后VM参数增加 --add-opens java.base/java.util.stream=ALL-UNNAMED，避免包异常
        // 创建可以为空的流对象
        Stream<Integer> streamNull = Stream.ofNullable(null);
        // dropWhile 使用一个断言作为参数，直到断言语句第一次返回 false 才返回给定 Stream 的子集
        Stream<Integer> dropWhile = stream.dropWhile(i -> i % 2 != 0);
        List<Integer> dropCollect = dropWhile.collect(Collectors.toList());
        System.out.println("dropWhile str : " + JSONUtil.toJsonStr(dropCollect));

        //takeWhile 使用一个断言作为参数，返回给定 Stream 的子集直到断言语句第一次返回 false
        Stream<Integer> takeWhile = Stream.of(1, 2, 3, 4, 5).takeWhile(i -> i % 2 != 0);
        List<Integer> takeCollect = takeWhile.collect(Collectors.toList());
        System.out.println("takeWhile str : " + JSONUtil.toJsonStr(takeCollect));

        // iterate 允许使用初始种子值创建顺序流，并迭代应用指定的下一个方法。 当指定的 hasNext 的 predicate 返回 false 时，迭代停止
        IntStream.iterate(3, x -> x < 10, x -> x + 3).forEach(System.out::println);
    }
}
