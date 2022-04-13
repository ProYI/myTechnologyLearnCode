package online.proyi.codeSegment.algorithm.basicDataStructure.arraysAndLinkedList.prefixArray_1.one;

public class NumArray1 {
    private int[] number;
    public NumArray1(int[] nums) {
        this.number = nums;
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += number[k];
        }
        return sum;
    }

    public static void main(String[] args) {
        var numArray1 = new NumArray1(new int[]{-2, 0, 3, -5, 2, -1});
        int result1 = numArray1.sumRange(0, 2);
        int result2 = numArray1.sumRange(2, 5);
        int result3 = numArray1.sumRange(0, 5);
        System.out.println("result 1 = " + result1);
        System.out.println("result 2 = " + result2);
        System.out.println("result 3 = " + result3);

    }
}
