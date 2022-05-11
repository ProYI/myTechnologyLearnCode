package online.proyi.normal.test.jdk11To17;

/**
 * 文本块 - 多行字符串
 * 避免大量使用转移符号
 */
public class TextBlock {
    public static void main(String[] args) {
        var str = """
                select *
                from User
                where id = "1"
                order by create_time desc
                """;
        System.out.println("text block content : " + str);
    }
}
