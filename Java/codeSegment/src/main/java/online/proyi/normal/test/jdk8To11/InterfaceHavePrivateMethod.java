package online.proyi.normal.test.jdk8To11;

/**
 * 允许在接口中使用私有方法
 */
public interface InterfaceHavePrivateMethod {
    default String testPrivateMethod(Integer flag) {
        return getPrivateMethodString();
    }

    private String getPrivateMethodString() {
        return "this is interface's private method string!";
    }
}

class ImplementClass implements InterfaceHavePrivateMethod {
    @Override
    public String testPrivateMethod(Integer flag) {
        if (flag == 0) {
            return InterfaceHavePrivateMethod.super.testPrivateMethod(0);
        }
        return "this is implementClass's string!";
    }

    public static void main(String[] args) {
        ImplementClass implementClass = new ImplementClass();
        String str1 = implementClass.testPrivateMethod(0);
        System.out.println(str1);
        String str2 = implementClass.testPrivateMethod(1);
        System.out.println(str2);
    }
}
