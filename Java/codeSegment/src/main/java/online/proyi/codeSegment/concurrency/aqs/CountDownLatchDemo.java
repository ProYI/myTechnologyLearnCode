package online.proyi.codeSegment.concurrency.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch
 * 中文翻译 闭锁
 * 英文翻译为 倒计时锁存器，是一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
 *
 * CountDownLatch有一个正数计数器
 * countDown()方法对计数器做减操作
 * await()方法等待计数器达到0
 * 所有await的线程都会阻塞直到计数器为0或者等待线程中断或者超时
 */
public class CountDownLatchDemo {
    private static int threadCount = 200;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
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
        // 所有thread全部阻塞，直到for循环内执行结束
        countDownLatch.await();
        System.out.println("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        Thread.sleep(100);
        System.out.println(threadNum);
        Thread.sleep(100);
    }
}
