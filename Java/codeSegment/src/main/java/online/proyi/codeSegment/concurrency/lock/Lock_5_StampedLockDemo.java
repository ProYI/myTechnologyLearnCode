package online.proyi.codeSegment.concurrency.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

public class Lock_5_StampedLockDemo {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static  int threadTotal = 200;

    private final static StampedLock LOCK = new StampedLock();

    public static int count = 0;

    public static void main(String[] args) throws Exception {

        // 定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量 指定并发数
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 定义计数器 指定请求总数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i=0; i<clientTotal; i++) {
            // 请求放入线程池中
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    // 释放进程
                    semaphore.release();
                } catch (Exception e) {
                    System.out.println("exception: " + e);
                }
                // 执行完后请求减1
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("count: " + count);
        executorService.shutdown();
    }

    private static void add() {
        long stamp = LOCK.writeLock();
        try {
            count++;
        } finally {
            LOCK.unlock(stamp);
        }
    }
}
