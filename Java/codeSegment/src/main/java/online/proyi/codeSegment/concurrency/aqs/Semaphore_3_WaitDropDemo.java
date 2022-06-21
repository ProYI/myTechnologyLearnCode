package online.proyi.codeSegment.concurrency.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 线程未获取到信号量，需要等待就舍弃
 */
public class Semaphore_3_WaitDropDemo {
    private static int threadCount = 20;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    // 如果线程能获取到许可 继续执行
                    // 如果无法获取许可 丢弃
                    if (semaphore.tryAcquire()) { // 尝试获取一个许可 可以传参，等待时间
                        test(threadNum);
                        semaphore.release(); // 释放许可
                    } else {
                        System.out.println(threadNum + "被丢弃");
                    }
                } catch (Exception e) {
                    System.out.println("exception " + e);
                }
            });
        }
        System.out.println("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        System.out.println(threadNum);
        Thread.sleep(100);
    }
}
