package org.example.netty.lambda.gougu;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 勾股数
 *
 * 古希腊数学家毕达哥拉斯发现了某些三元数(a, b, c)满足公式a * a + b * b =c * c，其中a、b、c都是整数。
 * 例如，(3, 4, 5)就是一组有效的勾股数，因为3 * 3 + 4 * 4 = 5 * 5或9 + 16 = 25。
 * 这样的三元数有无限组。例如，(5, 12, 13)、(6, 8, 10)和(7, 24, 25)都是有效的勾股
 * 数。
 */
public class GouguTest {
    public static void main(String[] args) {
//        IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a*a+b*b) % 1==0).mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a*a+b*b)})).collect(Collectors.toList());
        IntStream.rangeClosed(1, 100).boxed().flatMap(
                        a -> IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, (double) Math.sqrt(a*a+b*b)})
                                // 平方只需要取一次
                                .filter(t -> t[2]%1==0))
                .collect(Collectors.toList());

        // 流的创建
        // 1、从值创建
        Stream<String> stream = Stream.of("abc", "efg", "xyz");
        stream.map(String::toUpperCase).forEach(System.out::println);
        System.out.println("---------------------------------------");
        // 空的流
        Stream emptyStream = Stream.empty();

        // 2、由数组创建流
        int[] nums = {1, 2, 3, 4, 5, 6};
        int sum = Arrays.stream(nums).sum();
        System.out.println(sum);
        System.out.println("---------------------------------------");

        // 3、由文件生成流
        long uniqueWords = 0;
        try {
            Stream<String> lines = Files.lines(Paths.get("./.gitignore"), Charset.defaultCharset());
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
            System.out.println(uniqueWords);
            System.out.println("---------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4、由函数生成流，无限流
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        Stream.generate(() -> 2).limit(5).forEach(System.out::println);
        System.out.println("---------------------------------------");
    }
}
