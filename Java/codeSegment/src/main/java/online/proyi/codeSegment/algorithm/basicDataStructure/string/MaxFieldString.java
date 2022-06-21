package online.proyi.codeSegment.algorithm.basicDataStructure.string;

/**
 * 字典序最大的子序列
 * 输入
 * ababba
 * 输出
 * bbba
 * <p>
 * 示例2
 * 输入
 * abbcbccacbbcbaaba
 * 输出
 * cccccbba
 */
public class MaxFieldString {
    public static void main(String[] args) {
        String s1 = "abbcbccacbbcbaaba";
        String result = test1(s1);
        System.out.println("result: " + result);
    }

    private static String test1(String s1) {
        String[] m = s1.split("");

        StringBuilder builder = new StringBuilder();
        Integer flag = null;

        for (int i = m.length - 1; i >= 0; i--) {
            Integer now = (int) m[i].charAt(0);
            if (flag == null || now.compareTo(flag) >= 0) {
                builder.append(m[i]);
                flag = now;
            }
        }
        return builder.reverse().toString();
    }
}
