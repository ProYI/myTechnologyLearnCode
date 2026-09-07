package online.proyi.codeSegment.algorithm.helloAlgo.hash_table;

import java.util.ArrayList;
import java.util.List;


/**
 * 基于数组实现的哈希表
 */
public class ArrayHashMap {
    private List<Pair> buckets;

    public ArrayHashMap() {
        // 初始化 10个桶
        buckets = new ArrayList<Pair>();
        for (int i = 0; i < 10; i++) {
            buckets.add(null);
        }
    }

    // 哈希函数
    public int hashFunc(int key){
        return key % 10;
    }

    // 查询操作
    public String get(int key){
        int index = hashFunc(key);
        Pair pair = buckets.get(index);
        if(pair == null){
            return null;
        }
        return pair.val;
    }

    // 添加操作
    public void put(int key, String val){
        Pair pair = new Pair(key, val);
        int index = hashFunc(key);
        buckets.set(index, new Pair(key, val));
    }

    // 删除操作
    public void remove(int key){
        int index = hashFunc(key);
        // 置为 null ，代表删除
        buckets.set(index, null);
    }

    // 获取所有键值对
    public List<Pair> pairSet(){
        List<Pair> pairSet = new ArrayList<Pair>();
        for (Pair pair : buckets) {
            if (pair != null) {
                pairSet.add(pair);
            }
        }
        return pairSet;
    }

    // 获取所有键
    public List<Integer> keySet(){
        List<Integer> keySet = new ArrayList<>();
        for (Pair pair : buckets) {
            if (pair != null) {
                keySet.add(pair.key);
            }
        }
        return keySet;
    }

    // 获取所有值
    public List<String> valueSet() {
        List<String> valueSet = new ArrayList<>();
        for (Pair pair : buckets) {
            if (pair != null) {
                valueSet.add(pair.val);
            }
        }
        return valueSet;
    }

    // 打印哈希表
    public void print(){
        for (Pair pair : buckets) {
            System.out.println(pair.key + " -> " + pair.val);
        }
    }
}
