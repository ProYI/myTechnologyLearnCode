package online.proyi.codeSegment.algorithm.helloAlgo.array_linkedlist;

import java.util.concurrent.ThreadLocalRandom;

public class Array {
    /**
     * 初始化数组
     */
    public static void main(String[] args) {
        int[] arr = new int[5]; //对应值 {0,0,0,0,0}

        int[] nums = {1, 2, 3, 4, 5};

    }

    // 随机访问元素
    int randomAccess(int[] nums) {
        // 在区间 [0, nums.length) 中随机抽取一个数字
        int randomIndex = ThreadLocalRandom.current().nextInt(0, nums.length);
        // 获取并返回随机元素
        return nums[randomIndex];
    }

    // 插入元素
    void insert(int[] nums, int num, int index) {
        // 把索引 index 以及之后的所有元素向后移动一位
        for (int i = nums.length - 1; i > index; i--) {
            nums[i] = nums[i - 1];
        }
        nums[index] = num;
    }

    // 删除元素
    void remove(int[] nums, int index) {
        // index后所有元素向前移动一位
        for (int i = index; i < nums.length; i++) {
            nums[i] = nums[i + 1];
        }
    }

    // 遍历数组
    int find(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // 扩容数组
    int[] extend(int[] nums, int enlarge) {
        // 初始一个扩展长度后数组
        int[] res = new int[nums.length + enlarge];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i];
        }
        return res;
    }
}
