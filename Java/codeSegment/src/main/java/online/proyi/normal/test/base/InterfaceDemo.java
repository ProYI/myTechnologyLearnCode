package online.proyi.normal.test.base;

public interface InterfaceDemo {
    Integer a = 1;

    static void print() {
        System.out.println("这是接口内的静态方法");
    }
}

class InterfaceImpl implements InterfaceDemo {
    public static void main(String[] args) {
        InterfaceDemo.print();
    }
}
