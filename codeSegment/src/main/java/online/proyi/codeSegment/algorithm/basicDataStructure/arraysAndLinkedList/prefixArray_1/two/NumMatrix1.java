package online.proyi.codeSegment.algorithm.basicDataStructure.arraysAndLinkedList.prefixArray_1.two;

/**
 * 此题阅读理解
 * 获取方框内的所有元素之和，
 */
public class NumMatrix1 {
    // preSum 记录 矩阵[0,0,i,j]的总和
    private int[][] preSum;
    public NumMatrix1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 0 || n == 0) {
            return;
        }
        preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 计算 [0,0,i,j]矩阵的值
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + matrix[i - 1][j - 1] - preSum[i - 1][j - 1];
            }
        }
    }

    // 计算子矩阵
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // 目标矩阵是 由 四个相邻矩阵预算获得
        return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1];
    }

    public static void main(String[] args) {
        NumMatrix1 numMatrix = new NumMatrix1(new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}});
        int result1 = numMatrix.sumRegion(2, 1, 4, 3);
        int result2 = numMatrix.sumRegion(1, 1, 2, 2);
        int result3 = numMatrix.sumRegion(1, 2, 2, 4);
        System.out.println("result 1 : " + result1);
        System.out.println("result 2 : " + result2);
        System.out.println("result 3 : " + result3);


    }
}
