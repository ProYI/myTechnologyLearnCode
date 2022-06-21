package online.proyi.codeSegment.algorithm.basicDataStructure.arraysAndLinkedList.prefixArray_1.three;

import java.util.HashMap;

/**
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * <p>
 * K 表示
 */
public class Solution {
    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 1};
        int[] b = new int[]{1, 2, 3};
        int result1 = subarraySum(a, 2);
        System.out.println("result1 : " + result1);
        int result2 = subarraySum(b, 3);
        System.out.println("result2 : " + result2);
    }

    //    public static int subarraySum(int[] nums, int k) {
//        int n = nums.length;
//        int[] preSum = new int[n + 1];
//
//        for (int i = 0; i < n; i++) {
//            preSum[i + 1] = preSum[i] + nums[i];
//        }
//        int res = 0;
//        preSum[0] = 0;
//        // 穷举所有子数组
//        for (int i = 1; i <= n; i++) {
//            for (int j = 0; j < i; j++) {
//                if (preSum[i] - preSum[j] == k) {
//                    res++;
//                }
//            }
//        }
//        return res;
//    }
    public static int subarraySum(int[] nums, int k) {
        int n = nums.length;
//        int[] preSum = new int[n + 1];
        // map: 前缀和数组 -> 前缀和出现的次数
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        int res = 0, sum0_i = 0;
        for (int i = 0; i < n; i++) {
            sum0_i += nums[i];
            // sum0_j 就是 nums[0] + ... + nums[j]
            int sum0_j = sum0_i - k;
            // 如果前⾯有这个前缀和，则直接更新
            if (preSum.containsKey(sum0_j)) {
                // preSum(sum0_j) 就可以找到 和为K的子数组了。然后通过hashmap获取出现的次数，就可以知道有几个子数组
                res += preSum.get(sum0_j);
            }
            preSum.put(sum0_i, preSum.getOrDefault(sum0_i, 0) + 1);
        }
        return res;
    }
}
