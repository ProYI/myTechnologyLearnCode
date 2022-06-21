package online.proyi.normal.test.base;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] a = new int[] {3,5,7,1,2,9,6,8};
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int swap = a[i];
                    a[i] = a[j];
                    a[j] = swap;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
