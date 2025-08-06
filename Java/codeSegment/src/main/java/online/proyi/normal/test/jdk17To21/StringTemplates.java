package online.proyi.normal.test.jdk17To21;

import static java.lang.StringTemplate.STR;

/**
 * 字符串模板
 */
public class StringTemplates {
    public static void main(String[] args) {
        String name = "tom";
        int age = 18;

        String info = STR."name : \{name}, age:\{age}";
        System.out.println(info);
    }
}
