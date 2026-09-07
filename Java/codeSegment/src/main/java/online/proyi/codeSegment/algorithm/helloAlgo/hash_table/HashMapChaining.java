package online.proyi.codeSegment.algorithm.helloAlgo.hash_table;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

/**
 * 链式地址哈希表
 */
public class HashMapChaining {

    // 桶数组
    List<List<Pair>> buckets;

    // 键值对数量
    int size;

    // 哈希表容量
    int capacity;

    // 触发扩容的负载因子阈值
    double loadThres;

    // 扩容倍数
    int extendRatio;


    public HashMapChaining() {
        size = 0;
        capacity = 4;
        loadThres = 2.0 / 3.0;
        extendRatio = 2;
        buckets = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    // 哈希函数
    int hashFunc(int key) {
        return key % capacity;
    }

    // 负载因子
    double loadFactor() {
        return (double) size / capacity;
    }

    // 查询操作
    String get(int key) {
        int index = hashFunc(key);
        List<Pair> bucket = buckets.get(index);

        // 遍历桶，若找到key,则返回对应val
        for (Pair pair : bucket) {
            if (pair.key == key) {
                return pair.val;
            }
        }

        // 若未找到 key ，则返回 null
        return null;
    }

    // 添加操作
    void put(int key, String val) {
        // 当负载因子超过阈值时，执行扩容
        if (loadFactor() > loadThres) {
            extend();
        }

        int index = hashFunc(key);
        List<Pair> bucket = buckets.get(index);

        // 遍历桶，若遇到指定 key ，则更新对应 val 并返回
        for (Pair pair : bucket) {
            if (pair.key == key) {
                pair.val = val;
                return;
            }
        }

        Pair pair = new Pair(key, val);
        bucket.add(pair);
        size++;
    }

    // 扩容哈希表
    void extend() {
        // 暂存原哈希表
        List<List<Pair>> bucketsTmp = buckets;
        // 初始化扩容后的新哈希表
        capacity *= extendRatio;

        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
        size = 0;

        // 将键值对从原哈希表搬运至新哈希表
        for (List<Pair> bucket : bucketsTmp) {
            for (Pair pair : bucket) {
                put(pair.key, pair.val);
            }
        }
    }

    // 删除操作
    void  remove(int key) {
        int index = hashFunc(key);
        List<Pair> bucket = buckets.get(index);

        for (Pair pair : bucket) {
            if (pair.key == key) {
                bucket.remove(pair);
                size--;
                break;
            }
        }
    }

    // 打印哈希表
    void print() {
        for (List<Pair> bucket : buckets) {
            List<String> res = new ArrayList<>();
            for (Pair pair : bucket) {
                res.add(pair.key + " -> " + pair.val);
            }
            System.out.println(res);
        }
    }
}
