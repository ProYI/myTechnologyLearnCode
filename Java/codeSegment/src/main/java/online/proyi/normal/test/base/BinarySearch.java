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

        System.out.println(binarySearch2(new int[] {1, 2, 10 ,15, 100}, 15));
        System.out.println(binarySearch2(new int[] {1, 2, 10 ,15, 100}, -2));
        System.out.println(binarySearch2(new int[] {1, 2, 10 ,15, 100}, 101));
        System.out.println(binarySearch2(new int[] {1, 2, 10 ,15, 100}, 13));
        System.out.println(binarySearch2(new int[] {}, 13));
        System.out.println(binarySearch2(new int[] {12}, 13));
        System.out.println(binarySearch2(new int[] {13}, 13));
        System.out.println(binarySearch2(new int[] {12, 13}, 12));
        System.out.println(binarySearch2(new int[] {12, 13}, 13));
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

    /**
     * @param array
     * @param i
     * @return
     */
    private static Integer binarySearch2(int[] array, int i) {
        int a = 0;
        int b = array.length;
        while (a < b) {
            // 当array为null时，a = b = m = 0; m=0是 array[0]会导致数组越界, 所以为空时不能进入循环体
            // 当array为1个元素时，a= 0; b = m = 1;
            // 当array为2个元素时，a= 0; b = 2, m = 1;

            // (a + b) 可能会导致int溢出
//            int m = (a + b) / 2;
            int m = a + (b - a) / 2; // jdk写法，避免int长度超出

            if (i < array[m]) {
                b = m;
            } else if (i > array[m]) {
                a = m + 1;
            } else {
                return m;
            }
        }
        // 未匹配到
        return -1;
    }
}
