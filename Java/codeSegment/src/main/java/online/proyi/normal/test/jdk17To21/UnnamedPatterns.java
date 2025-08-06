package online.proyi.normal.test.jdk17To21;

/**
 * 下划线通配符
 */
public class UnnamedPatterns {
    public static void main(String[] args) {
        Object obj = "Hello";

        if (obj instanceof String _) { // 忽略变量名
            System.out.println("It's a string");

        }
    }
}
