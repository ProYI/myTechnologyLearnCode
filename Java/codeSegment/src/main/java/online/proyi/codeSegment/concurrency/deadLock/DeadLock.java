package online.proyi.codeSegment.concurrency.deadLock;

/**
 * 死锁
 * 当DeadLock类的对象flag==1时（thread1），先锁定o1,睡眠1000毫秒
 * 而thread在睡眠的时候另一个flag==0的对象（thread2）线程启动，先锁定o2,睡眠1000毫秒
 * thread1睡眠结束后需要锁定o2才能继续执行，而此时o2已被thread2锁定；
 * thread2睡眠结束后需要锁定o1才能继续执行，而此时o1已被thread1锁定；
 * thread1、thread2相互等待，都需要得到对方锁定的资源才能继续执行，从而死锁。
 */
public class DeadLock implements Runnable {
    public int flag = 1;
    //静态对象是类的所有对象共享的
    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        System.out.println("flag: " + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("o1-o2");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("o2-o1");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock l1 = new DeadLock();
        DeadLock l2 = new DeadLock();
        l1.flag=0;
        l2.flag=1;
        Thread thread1 = new Thread(l1);
        Thread thread2 = new Thread(l2);

        thread1.start();
        thread2.start();
        //thread1,thread2都处于可执行状态，但JVM线程调度先执行哪个线程是不确定的。
        //thread2的run()可能在thread2的run()之前运行

    }
}
