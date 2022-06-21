package online.proyi.codeSegment.concurrency.unsafeCase;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class StringBufferDemo {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static  int threadTotal = 200;

    public static java.lang.StringBuffer stringBuffer = new java.lang.StringBuffer();

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
                    update();
                    // 释放进程
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 执行完后请求减1
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("size: " + stringBuffer.length());
    }

    private static void update() {
        // stringBuffer 是线程安全的
        stringBuffer.append("1");
    }
}
