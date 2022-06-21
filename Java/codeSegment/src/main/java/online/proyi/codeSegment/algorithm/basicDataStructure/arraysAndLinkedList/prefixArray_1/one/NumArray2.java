package online.proyi.codeSegment.algorithm.basicDataStructure.arraysAndLinkedList.prefixArray_1.one;

public class NumArray2 {
    private int[] preSum;
    public NumArray2(int[] nums) {
//        preSum[0] = 0 便于计算累加和;
        preSum = new int[nums.length + 1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i-1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        // 因为 pre[1] 是 pre[0] + num[0]
        // 若 求 num[1]到num[4] 则 num[4] = pre[3] + nums[4] = pre[5] = pre[1]  + num[1] + num[2] + num[3] + num[4]
        // 所以 pre[5] - pre[1] = num[1] + num[2] + num[3] + num[4] 即 pre[j + 1] - pre[i]
        return preSum[j+1] - preSum[i];
    }

    public static void main(String[] args) {
        var numArray1 = new NumArray2(new int[]{-2, 0, 3, -5, 2, -1});
        int result1 = numArray1.sumRange(0, 2);
        int result2 = numArray1.sumRange(2, 5);
        int result3 = numArray1.sumRange(0, 5);
        System.out.println("result 1 = " + result1);
        System.out.println("result 2 = " + result2);
        System.out.println("result 3 = " + result3);

    }
}
