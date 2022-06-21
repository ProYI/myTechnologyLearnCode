package online.proyi.normal.test.jdk8To11;

/**
 * 局部变量类型推倒判断
 */
public class LocalVariableInfer {
    public static void main(String[] args) {
        var a = "abc";
        var b = Boolean.valueOf(false);
        var c = a + b;
        var d = Long.valueOf(123);

        System.out.println(a.getClass().getTypeName());
        System.out.println(b.getClass().getTypeName());
        System.out.println(c.getClass().getTypeName());
        System.out.println(d.getClass().getTypeName());
    }
}
