package online.proyi.codeSegment.concurrency.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier
 * 字面意思 可重复使用的栅栏
 *
 * 利用CyclicBarrier类可以实现一组线程相互等待，当所有线程都到达某个屏障点后再进行后续的操作
 *
 * CyclicBarrier 和 CountDownLatch 类似
 *
 * 区别是CyclicBarrier可以重复使用，而CountDownLatch只能使用一次
 * CountDownLatch 基于 AQS 的共享模式的使用，而 CyclicBarrier 基于 Condition 来实现
 *
 */
public class CyclicBarrier_1_Demo {

    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            final int threadNum = i;
            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    System.out.println("exception " + e);
                }
            });
        }
        executor.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        System.out.println(threadNum + " is ready");
        barrier.await();
        System.out.println(threadNum + " continue");
    }
}
