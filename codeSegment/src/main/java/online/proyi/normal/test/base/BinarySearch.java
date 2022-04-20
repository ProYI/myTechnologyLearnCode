package online.proyi.normal.test.base;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] a = new int[] {3,5,7,1,2,9,6,8};
        System.out.println(Arrays.toString(a));
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        int param1 = 9;
        Integer index1 = binarySearch(a, param1);
        // 排序从1开始，返回0说明未查询到
        System.out.println(param1 + "在数组中的位置是" + (index1 + 1));
    }

    private static Integer binarySearch(int[] a, int i) {
        int max = a.length - 1, min = 0, mid = (max + min) / 2;
        while (a[mid] != i) {
            if (i > a[mid]) {
                min = mid + 1;
            }
            if (i < a[mid]) {
                max = mid -1;
            }
            if (max < mid) {
                // 表示未查到，不存在
                return -1;
            }
            mid = (max + min) / 2;
        }
        return mid;
    }
}
