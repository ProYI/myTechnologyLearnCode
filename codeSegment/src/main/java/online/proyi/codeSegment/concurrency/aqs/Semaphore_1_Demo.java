package online.proyi.codeSegment.concurrency.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore
 * 信号量
 *
 * 可以通过其限制执行的线程数量，达到限流的效果
 * 当一个线程执行时先通过其方法进行获取许可操作，获取到许可的线程继续执行业务逻辑
 * 当线程执行完成后进行释放许可操作，未获取达到许可的线程进行等待或者直接结束
 */
public class Semaphore_1_Demo {
    private static int threadCount = 20;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    // 获取一个许可
                    semaphore.acquire();
                    test(threadNum);
                    // 释放一个许可
                    semaphore.release();
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
