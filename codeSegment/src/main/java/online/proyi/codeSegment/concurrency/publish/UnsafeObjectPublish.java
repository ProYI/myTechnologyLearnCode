package online.proyi.codeSegment.concurrency.publish;

import java.util.Arrays;

/**
 * 不安全的对象发布
 */
public class UnsafeObjectPublish {
    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafeObjectPublish unsafePublish = new UnsafeObjectPublish();
        System.out.println(Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        System.out.println(Arrays.toString(unsafePublish.getStates()));

        // 通过对象的get方法拿到 数组域的引用，在其他任意线程对数组进行修改，导致数组值发生改变
    }
}
