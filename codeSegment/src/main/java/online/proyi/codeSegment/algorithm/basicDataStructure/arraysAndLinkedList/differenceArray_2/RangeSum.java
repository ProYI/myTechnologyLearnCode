package online.proyi.codeSegment.algorithm.basicDataStructure.arraysAndLinkedList.differenceArray_2;

import cn.hutool.core.util.ArrayUtil;

public class RangeSum {
    public static void main(String[] args) {
        int[] initArray = new int[5];
        Difference difference = new Difference(initArray);
        Integer[][] update = new Integer[][]{{1, 3, 2}, {2, 4, 3}, {0, 2, -2}};
        for (int i = 0; i < update.length; i++) {
            difference.increment(update[i][0], update[i][1], update[i][2]);
            System.out.println((i + 1) + "次操作后的数组: " + ArrayUtil.toString(difference.result()));
        }
    }
}

class Difference {
    // 差分数组
    private int[] diff;

    // 输入一个初始化数组，区间操作在差分数组中进行
    public Difference(int[] nums) {
        assert nums.length > 0;
        this.diff = new int[nums.length];

        // 根据初始数组构造差分数组
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] = nums[i - 1];
        }
    }

    // 给闭区间 [i,j]增加val  (可以为负数)
    public void increment(int i, int j, int val) {
        diff[i] += val;

        // 当 j+1 >= diff.length 时，说明是对 nums[i] 及以后的整个数组都进⾏修改，那么就不需要再给 diff数组减 val
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    // 返回结果数组
    public int[] result() {
        int[] res = new int[diff.length];
        // 根据差分数组结果，反推构造数组结果
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }
}