package online.proyi.normal.test.jdk17To21;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class VirtualThread {

    public static void main(String[] args) throws InterruptedException {
        Thread v1 = Thread.ofVirtual().name("v1").unstarted(() -> System.out.println("Hello Virtual Thread!"));
        v1.start();

        Thread.startVirtualThread(() -> {
            System.out.println("Hello Virtual Thread! v2");
            System.out.println("v2 Is virtual thread : " + Thread.currentThread().isVirtual());
        }).join();



        Thread.Builder builder = Thread.ofVirtual().name("v3-", 0);
        Runnable task = () -> {
            var threadName = Thread.currentThread().getName();
            System.out.println(threadName + " run...");
            System.out.println("Is " + threadName + " virtual thread : " + Thread.currentThread().isVirtual());
        };
        Thread v3_0 = builder.start(task);
        Thread v3_1 = builder.start(task);

        // 打印当前jvm中线程信息
        Thread.getAllStackTraces().keySet().forEach(System.out::println);

        try {
            // 等待这些虚拟线程运行结束
            v1.join();
            v3_0.join();
            v3_1.join();
            MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LocalDateTime startTime = LocalDateTime.now();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<Boolean>> futures = new ArrayList<>();
            // 创建100万个虚拟线程
            for (int i = 0; i < 1_000_000; i++) {
                futures.add(executor.submit(() -> {
                    // 打印当前线程信息
                    System.out.println(Thread.currentThread().threadId() + " isVirtual:" + Thread.currentThread().isVirtual());
                    Thread.sleep(1000);
                    return true;
                }));
            }
            // 遍历并获取这100万虚拟线程的执行结果
            futures.forEach(f -> {
                try {
                    f.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            executor.shutdown();
        }
        LocalDateTime endTime = LocalDateTime.now();
        // 计算耗时
        System.out.println("耗时:" + Duration.between(startTime, endTime).toMillis() + "毫秒");
    }
}
