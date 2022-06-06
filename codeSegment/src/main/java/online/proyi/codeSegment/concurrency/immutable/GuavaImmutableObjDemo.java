package online.proyi.codeSegment.concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class GuavaImmutableObjDemo {
    private final static ImmutableList LIST = ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);

    private final static ImmutableSet SET = ImmutableSet.copyOf(LIST);

    private final static ImmutableMap<Integer, Integer> MAP = ImmutableMap.of(1,2,3,4);

    private final static ImmutableMap<Integer, Integer> MAP2 = ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4).put(5, 6).build();

    public static void main(String[] args) {
        // 不可变对象 抛出异常，废弃方法
        LIST.add(1);
    }
}
