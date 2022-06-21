package online.proyi.codeSegment.concurrency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Lock_6_AQS_SyncQueue_ConditionDemo {


    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        // 1 2 3 4 表示输出顺序
        new Thread(() -> {
            try {
                reentrantLock.lock(); // 加入AQS等待队列
                System.out.println("wait signal"); // 1
                condition.await(); // 线程从AQS对列移除，对应操作就是锁的释放 接着加入condition等待队列
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get signal"); // 4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock(); // 加入AQS等待队列
            System.out.println("get lock"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll(); // 调用 发送信号的方法
            System.out.println("send signal ~"); // 3
            reentrantLock.unlock();
        }).start();
    }
}
