package online.proyi.codeSegment.concurrency.publish;

/**
 * 对象发布 - 对象逸出
 */

public class ObjectEscape {
    private int thisCanBeEscape = 0;

    public ObjectEscape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            System.out.println(" " + ObjectEscape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new ObjectEscape();
        // 内部类的实例中包含了对封装实例的隐含的引用，对象没有在被正确构造完成之前就会被发布，有可能有不安全的因素
        // 一个导致this引用在构造期间逸出的错误，ObjectEscape构造函数过程中相当于启动了一个线程
    }
}
