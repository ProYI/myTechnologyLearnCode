package online.proyi.normal.test.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 已知一个 HashMap<Integer，User>集合， User 有 name（String）和 age（int）属性。
 *
 * 请写一个方法实现对HashMap 的排序功能
 * 该方法接收 HashMap<Integer，User>为形参，返回类型为 HashMap<Integer，User>，
 *
 * 要求对 HashMap 中的 User 的 age 倒序进行排序。排序时 key=value 键值对不得拆散
 */
public class MapSort {
    public static void main(String[] args) {
        HashMap<Integer, User> map = new HashMap<>();
        map.put(1, new User("张三", 25));
        map.put(3, new User("李四", 22));
        map.put(2, new User("王五", 28));
        HashMap<Integer, User> sortMap = mapSort(map);
        System.out.println(sortMap.toString());
        HashMap<Integer, User> sortMap2 = mapSort2(map);
        System.out.println(sortMap2.toString());

    }

    private static HashMap<Integer, User> mapSort(HashMap<Integer, User> map) {
        Set<Map.Entry<Integer, User>> entrySet = map.entrySet();

        List<Map.Entry<Integer, User>> list = new ArrayList<Map.Entry<Integer, User>>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                return o2.getValue().getAge() - o1.getValue().getAge();
            }
        });

        LinkedHashMap<Integer, User> newMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, User> entry : list) {
            newMap.put(entry.getKey(), entry.getValue());
        }
        return newMap;
    }

    private static HashMap<Integer, User> mapSort2(HashMap<Integer, User> map) {
        LinkedHashMap<Integer, User> newMap = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue().getAge() - o1.getValue().getAge())
                .forEach(entry -> newMap.put(entry.getKey(), entry.getValue()));
        return newMap;
    }
}

class User {
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}