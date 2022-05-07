package online.proyi.codeSegment.thread.createthread.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池创建线程
 */
public class ThreadPool {
    public static void main(String[] args) {
        /**
         * 线程池内部还是 new Thread（传入runnable参数），所以本质还是未改变
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Task());
        }
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
