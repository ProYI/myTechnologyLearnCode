package online.proyi.codeSegment.concurrency.threadPool;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            executorService1.execute(() -> {
                System.out.println("cached task: " + index + " , ThreadName: " + Thread.currentThread().getName());
            });
        }
        executorService1.shutdown();

        ExecutorService executorService2 = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            executorService2.execute(() -> {
                System.out.println("fixed task: " + index + " , ThreadName: " + Thread.currentThread().getName());
            });
        }
        executorService2.shutdown();

        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            executorService3.execute(() -> {
                System.out.println("single task: " + index + " , ThreadName: " + Thread.currentThread().getName());
            });
        }
        executorService3.shutdown();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        // 此线程池有了调度的概念
//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("schedule run");
//            }
//        }, 3, TimeUnit.SECONDS);

        System.out.println("-------------------------------------");

        // 以指定速率执行任务 延迟1s 每隔3s执行任务
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("schedule run : " + DateUtil.formatDateTime(new Date()));
            }
        }, 1, 3, TimeUnit.SECONDS);
//        executorService.shutdown();

        // 定时器
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timer run : " + DateUtil.formatDateTime(new Date()));
            }
        }, new Date(), 5*1000);

    }
}
