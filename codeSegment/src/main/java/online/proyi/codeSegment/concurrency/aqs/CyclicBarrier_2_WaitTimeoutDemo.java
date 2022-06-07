package online.proyi.codeSegment.concurrency.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 当前线程等待超时，则抛出TimeoutException异常
 *
 * 当前线程抛出 TimeoutException 异常时，其他线程会抛出 BrokenBarrierException 异常
 */
public class CyclicBarrier_2_WaitTimeoutDemo {

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
        // await指定传入等待时间
        try {
            barrier.await(3000, TimeUnit.MILLISECONDS);
        } catch (BrokenBarrierException | TimeoutException e) {
            System.out.println(threadNum + " throw BrokenBarrierException: " + e);
        }
        System.out.println(threadNum + " continue");
    }
}
