package online.proyi.codeSegment.concurrency.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedExample {
    // 修饰一个代码块
    public void test1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " : test1 -" + i);
            }
        }

    }

    // 修饰一个代码块
    public void testClass() {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " : testClass -" + i);
            }
        }

    }

    // 修饰一个方法
    public synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " : test2 - " + i);
        }
    }

    // 修饰静态方法
    public static synchronized void testStatic(int j) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " : testStatic "+ j + " - " + i);
        }
    }

    public static void main1(String[] args) {
        SynchronizedExample example1 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test1();
        });
        executorService.execute(() -> {
            example1.test1();
        });
        // 执行结果 线程1先执行完释放锁后线程2再执行
    }

    public static void main2(String[] args) {
        SynchronizedExample example1 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test2();
        });
        executorService.execute(() -> {
            example1.test2();
        });
        // 执行结果 线程1先执行完释放锁后线程2再执行
    }

    public static void main3(String[] args) {
        SynchronizedExample example1 = new SynchronizedExample();
        SynchronizedExample example2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test1();
        });
        executorService.execute(() -> {
            example2.test1();
        });
        // 执行结果 线程1和线程2打印结果交错
        // 说明 synchronized (this) 只锁定住当前对象，不同对象并未使用同一个锁
        // 每个线程争抢CPU执行权限不同，导致打印错乱
    }
    public static void main4(String[] args) {
        SynchronizedExample example1 = new SynchronizedExample();
        SynchronizedExample example2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.testClass();
        });
        executorService.execute(() -> {
            example2.testClass();
        });
        // 执行结果 线程1和线程2顺序打印，说明获取到锁后执行完内容才释放
        // synchronized(xxx.class) 字节码上锁，不同对象共享一个锁
    }

    public static void main(String[] args) {
        SynchronizedExample example1 = new SynchronizedExample();
        SynchronizedExample example2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            testStatic(10);
        });
        executorService.execute(() -> {
            testStatic(10);
        });
        // 执行结果 线程1和线程2顺序打印，说明静态方法获取到锁后执行完内容才释放
    }
}
