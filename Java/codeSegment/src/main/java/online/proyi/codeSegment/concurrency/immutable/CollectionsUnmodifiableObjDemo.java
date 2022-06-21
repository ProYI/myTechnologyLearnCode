package online.proyi.codeSegment.concurrency.immutable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CollectionsUnmodifiableObjDemo {

    // 此处没有被static修饰
    private static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        // 被Collections.unmodifiableMap处理过的map将不可变
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        // map在更新操作时抛出异常
        map.put(1, 3);
        System.out.println(" " + map.get(1));
    }

}
