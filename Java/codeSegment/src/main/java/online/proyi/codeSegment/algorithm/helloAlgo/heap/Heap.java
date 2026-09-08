package online.proyi.codeSegment.algorithm.helloAlgo.heap;

import lombok.val;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Heap {
    static void main() {
        /* 初始化堆 */
        // 初始化小顶堆
        // 优先队列（priority queue）
        Queue<Integer> minHeap = new PriorityQueue<>();
        // 初始化大顶堆（使用 lambda 表达式修改 Comparator 即可）
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        /* 元素入堆 */
        maxHeap.offer(1);
        maxHeap.offer(3);
        maxHeap.offer(2);
        maxHeap.offer(5);
        maxHeap.offer(4);
        /* 获取堆顶元素 */
        int peek = maxHeap.peek(); // 5
        System.out.println("peek:" + peek);

        /* 堆顶元素出堆 */
        // 出堆元素会形成一个从大到小的序列
        for (int i = 0; i < 5; i++) {
            System.out.println(STR."max: \{maxHeap.poll()}");
        }

        /* 获取堆大小 */
        int size = maxHeap.size();
        /* 判断堆是否为空 */
        boolean isEmpty = maxHeap.isEmpty();

        /* 输入列表并建堆 */
        minHeap = new PriorityQueue<>(Arrays.asList(1, 3, 2, 5, 4));
        for (int i = 0; i < 5; i++) {
            System.out.println(STR."min: \{minHeap.poll()}");
        }
    }

    /* 获取左子节点的索引 */
    int left(int i) {
        return 2 * i + 1;
    }

    /* 获取右子节点的索引 */
    int right(int i) {
        return 2 * i + 2;
    }

    /* 获取父节点的索引 */
    int parent(int i) {
        // 向下整除
        return (i - 1) / 2;
    }
}
