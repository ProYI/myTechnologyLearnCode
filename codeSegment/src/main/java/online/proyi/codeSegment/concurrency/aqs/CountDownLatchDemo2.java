package online.proyi.codeSegment.concurrency.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch 可以指定阻塞时间
 * 若时间超过设定，未返回直接返回false
 */
public class CountDownLatchDemo2 {
    private static int threadCount = 200;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            Thread.sleep(1);
            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    System.out.println("exception " + e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        System.out.println("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) {
        System.out.println(threadNum);
    }
}
