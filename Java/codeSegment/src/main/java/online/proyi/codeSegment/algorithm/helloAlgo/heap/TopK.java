package online.proyi.codeSegment.algorithm.helloAlgo.heap;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// 基于堆更加高效地解决 Top‑k 问题
public class TopK {
    static void main() {
        int[] nums = new int[]{10, 8, 1, 3, 5, 6, 11, 20, 9, 7};
        Queue<Integer> integers = topKHeap(nums, 3);
        integers.forEach(System.out::println);
    }

    public static Queue<Integer> topKHeap(int[] nums, int k) {
        // 初始化小顶堆
        Queue<Integer> heap = new PriorityQueue<Integer>();
        // 将数组的前 k 个元素入堆
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        // 从第 k+1 个元素开始，保持堆的长度为 k
        for (int i = k; i < nums.length; i++) {
            // 若当前元素大于堆顶元素，则将堆顶元素出堆、当前元素入堆
            if (nums[i] > heap.peek()) {
                heap.poll();
                heap.offer(nums[i]);
            }
        }
        return heap;
    }
}
