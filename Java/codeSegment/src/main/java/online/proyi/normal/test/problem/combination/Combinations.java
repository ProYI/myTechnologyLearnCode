package online.proyi.normal.test.problem.combination;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 列出所有组合
 *
 * [1,2,3,4] 中取 2个数
 */
public class Combinations {

    public static void main(String[] args) {
        Combinations combinations = new Combinations();

        combinations.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 2);
        combinations.combinations(new ArrayList<>(), new ArrayList<>(), 2);
        combinations.combinations(new ArrayList<>(), new ArrayList<>(), 0);
        combinations.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 1);
        combinations.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 0);
        combinations.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 4);
    }

    private void combinations(List<Integer> selectedList, List<Integer> data, int n) {
        if (n == 0) {
            // n为0是因为 需要选择的元素已经选完
            for (Integer i : selectedList) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();

            return;
        }

        if (data.isEmpty()) {
            return;
        }


        // 如果选择了第一个元素
        selectedList.add(data.get(0));
        combinations(selectedList, data.subList(1, data.size()), n-1);

        // 如果没有选择第一个元素
        selectedList.remove(selectedList.size() - 1);
        combinations(selectedList, data.subList(1, data.size()), n);

    }
}
