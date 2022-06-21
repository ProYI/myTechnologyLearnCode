package online.proyi.codeSegment.concurrency.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {


    public static void main(String[] args) throws InterruptedException {
        ThreadLocalHoler.add(100L);

        Long threadLocalId = ThreadLocalHoler.get();
        System.out.println(Thread.currentThread().getName() + ":threadlocal id : " + threadLocalId);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            Long threadLocal = ThreadLocalHoler.get();
            System.out.println(Thread.currentThread().getName() + ":threadlocal id : " + threadLocal);

            ThreadLocalHoler.add(999L);
            Long threadLocalIdAfter = ThreadLocalHoler.get();
            System.out.println(Thread.currentThread().getName() + ":threadlocal id : " + threadLocalIdAfter);

        });
        Thread.sleep(1000);
        ThreadLocalHoler.remove();
        Long threadLocalIdAfter = ThreadLocalHoler.get();
        System.out.println(Thread.currentThread().getName() + ":threadlocal id : " + threadLocalIdAfter);
        executorService.shutdown();
    }
}

class ThreadLocalHoler {
    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<>();

    public static void add(Long id) {
        THREAD_LOCAL.set(id);
    }

    public static Long get() {
        return THREAD_LOCAL.get();
    }
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}