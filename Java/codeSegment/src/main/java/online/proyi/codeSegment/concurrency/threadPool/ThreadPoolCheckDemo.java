package online.proyi.codeSegment.concurrency.threadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * 1~3 任务直接获取到线程开始工作
 * 4~103 任务进入 等待队列
 * 104、105 在判断核心线程已满、等待队列已满，但为达到最大线程数量，会直接创建线程 执行任务
 *
 * 106任务 直接根据设置的策略
 * （CallerRunsPolicy）进行处理 （让提交任务的线程自己去执行任务）。导致提交任务的线程(主线程)被阻塞，107未遍历
 *  106任务执行完成后,107任务才能继续执行
 *
 * （AbortPolicy）抛出RejectedExecutionException
 * （DiscardPolicy）丢弃任务
 *

 */
public class ThreadPoolCheckDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3, 5, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                new ThreadPoolExecutor.DiscardPolicy());

        System.out.println("Starting tasks...");

        for (int i = 1; i <= 108; i++) {
            final int taskId = i;
            try {
                executor.submit(() -> {
                    try {
                        System.out.println("Task " + taskId + " is running in " + Thread.currentThread().getName());
                        Thread.sleep(10000); // 模拟任务执行
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                System.out.println("Task " + taskId + " submitted. Queue size: " + executor.getQueue().size());
            } catch (RejectedExecutionException rejectedExecutionException) {
                System.out.println("Task " + taskId + " was rejected due to: " + rejectedExecutionException.getMessage());
            }
        }

        executor.shutdown();
    }
}