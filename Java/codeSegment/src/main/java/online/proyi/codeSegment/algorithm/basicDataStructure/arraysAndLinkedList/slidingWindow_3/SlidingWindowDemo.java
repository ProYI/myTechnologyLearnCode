package online.proyi.codeSegment.algorithm.basicDataStructure.arraysAndLinkedList.slidingWindow_3;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindowDemo {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        String result = slidingWindow(s, t);
        System.out.println("最小子串: " + result);
    }
    public static String slidingWindow(String s, String t) {
        // needs 和 window 相当于计数器
        // 记录 T 中字符出现次数
        Map<Character, Integer> needs = new HashMap<>();
        //「窗⼝」中的相应字符 的出现次数
        Map<Character, Integer> window = new HashMap<>();

        char[] needsCharArray = t.toCharArray();
        for (char c : needsCharArray) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }

        // 初始化 索引左闭右开区间 [left, right) 称为⼀个「窗⼝」
        //  valid 变量表示窗⼝中满⾜ need 条件的字符个数，如果 valid 和 need.size 的⼤⼩相同，则说明窗 ⼝已满⾜条件，已经完全覆盖了串T
        int left = 0, right = 0, valid = 0;

        // 记录最⼩覆盖⼦串的起始索引及⻓度
        int start = 0, len = Integer.MAX_VALUE;

        char[] sCharArray = s.toCharArray();
        while (right < s.length()) {
            // c是移入窗口的字符
            char c = sCharArray[right];
            // 右移窗口
            right++;

            // 窗口内数据的更新
            if (needs.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);

                if (window.get(c).equals(needs.get(c))) {
                    valid++;
                }
            }

            // 判断左侧窗口是否要收缩
            while (valid == needs.size()) {
                // 已满足所有元素条件
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                // d 是将移出窗口的字符
                char d = sCharArray[left];
                // 左侧移动
                left++;
                // 窗口内的数据更新
                if (needs.containsKey(d)) {
                    if (window.get(d).equals(needs.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        // 返回最小覆盖子串
        return len == Integer.MAX_VALUE ? "" : s.substring(start, right);
    }
}
