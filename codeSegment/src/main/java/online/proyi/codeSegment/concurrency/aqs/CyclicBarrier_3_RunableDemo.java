package online.proyi.codeSegment.concurrency.aqs;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrier_3_RunableDemo {
    /**
     * 声明CyclicBarrier指定Runable
     * 当线程到达屏障时，优先执行里面的runable
     */
    private static CyclicBarrier barrier = new CyclicBarrier(5, () -> {
        System.out.println("callback is running");
    });

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
