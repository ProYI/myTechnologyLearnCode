package online.proyi.normal.test.jdk17To21;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SequencedCollections {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("a", "b", "c"));
        System.out.println(list.getFirst());  // a
        System.out.println(list.getLast());   // c
        System.out.println(JSONUtil.toJsonStr(list.reversed()));
    }
}
