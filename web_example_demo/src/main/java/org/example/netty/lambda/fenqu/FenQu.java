package org.example.netty.lambda.fenqu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FenQu {
    public static void main(String[] args) throws JsonProcessingException {
        /**
         * 分区是分组的特殊情况
         * 分区函数返回一个布尔值，这意味着得到的分组Map的键类型是Boolean，于是它最多可以分为两组
         * true是一组，false是一组
         */
        //将数字按质数和非质数分区
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        Map<Boolean, List<Integer>> map = partitionPrimes(100);
        System.out.println("质数: " + objectWriter.writeValueAsString(map.get(true)));
        System.out.println("非质数: " + objectWriter.writeValueAsString(map.get(false)));
    }

    /**
     * 接受参数int n，并将前n个自然数分为质数和非质数。但首先，找出能够测试某一个待测数字是否是质数的谓词
     *
     * 仅测试小于等于待测数平方根的因子
     */
    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.range(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(FenQu::isPrime));
    }
}
