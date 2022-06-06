package online.proyi.codeSegment.concurrency.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanDemo {

    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws Exception {
        // 定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量 指定并发数
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 定义计数器 指定请求总数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            // 请求放入线程池中
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
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
        System.out.println("isHappened: " + isHappened.get());
    }

    private static void test() {
        if (isHappened.compareAndSet(false, true)) {
            System.out.println("execute");
        }
    }
}
