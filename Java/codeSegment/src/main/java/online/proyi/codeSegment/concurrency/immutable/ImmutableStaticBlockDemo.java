package online.proyi.codeSegment.concurrency.immutable;

import java.util.HashMap;
import java.util.Map;

// 线程不安全
public class ImmutableStaticBlockDemo {
    private final static Integer a = 1;
    private final static String b = "2";

    // map的引用地址没有变，但是map内容发生了改变
    private final static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        map.put(1, 3);
        // 输出结果为3，表示main修改了内容
        System.out.println(" " + map.get(1));
    }

    private void test(final int a) {
//        a = 1;
    }
}
