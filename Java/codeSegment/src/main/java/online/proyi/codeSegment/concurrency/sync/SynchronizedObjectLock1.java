package online.proyi.codeSegment.concurrency.sync;

public class SynchronizedObjectLock1 implements Runnable {
    static SynchronizedObjectLock1 instance = new SynchronizedObjectLock1();

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("我是线程" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }
    }

    public static void main(String[] args) {
        // 因为 instance是静态的，所以 t1和t2 this 锁住的是同一个对象
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
    }
}
