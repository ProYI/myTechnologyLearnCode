package online.proyi.codeSegment.concurrency.syncContainer;

import java.util.Vector;

/**
 * 同步容器也有线程不安全的情况
 */

public class VectorThreadUnsafeDemo {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i= 0; i < 10; i++ ) {
                vector.add(i);
            }

            // 抛出 ArrayIndexOutOfBoundsException
            // 复合操作，synchronized无法保证线程安全
            Thread thread1 = new Thread(() -> {
                for (int i= 0; i < vector.size(); i++ ) {
                    vector.remove(i);
                }
            });
            Thread thread2 = new Thread(() -> {
                for (int i= 0; i < vector.size(); i++ ) {
                    vector.get(i);
                }
            });

            thread1.start();
            thread2.start();
        }
    }
}
