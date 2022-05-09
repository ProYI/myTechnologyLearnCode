package online.proyi.codeSegment.thread.stopThread.useVolatile;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 描述
 * 使用interrupt()方法正确结束线程
 */
public class WrongWayVolatileFixed {
    public static void main(String[] args) throws InterruptedException {
        // 初始化仓库队列
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        Producer1 producer1 = new Producer1(storage);
        Thread producerThread = new Thread(producer1);
        producerThread.start();

        Thread.sleep(1000);

        Consumer1 consumer = new Consumer1(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了");
        // 一旦消费者不需要更多数据，生产者停止生产，避免浪费
        producerThread.interrupt();
    }
}

/**
 * 生产者
 */
class Producer1 implements Runnable {
    // 声明阻塞队列
    BlockingQueue storage;

    public Producer1(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    storage.put(num);
                    System.out.println(num + "是100的倍数， 被放入仓库中");
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者结束运行");
        }
    }
}

/**
 * 消费者
 */
class Consumer1 {
    BlockingQueue storage;

    public Consumer1(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        }
        return true;
    }
}