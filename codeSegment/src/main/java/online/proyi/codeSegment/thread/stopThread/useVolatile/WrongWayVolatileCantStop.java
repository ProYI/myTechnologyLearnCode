package online.proyi.codeSegment.thread.stopThread.useVolatile;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 描述 演示用volatile的局限
 * part2 陷入阻塞时，volatile是无法结束线程的
 * 此例中，生产者的生产速度很快，消费者消费速度很慢，所以阻塞队列后，生产者会阻塞，等待消费者进一步消费
 */
public class WrongWayVolatileCantStop {
    public static void main(String[] args) throws InterruptedException {
        // 初始化仓库队列
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();

        Thread.sleep(1000);

        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了");
        // 一旦消费者不需要更多数据，生产者停止生产，避免浪费
        producer.canceled = true;
    }
}

/**
 * 生产者
 */
class Producer implements Runnable {
    // 声明阻塞队列
    BlockingQueue storage;
    public volatile boolean canceled = false;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            /**
             * 当队列被阻塞时，无法获取到while条件上的中断指令，线程也无法停止
             */
            while (num <= 10000 && !canceled) {
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
class Consumer {
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        }
        return true;
    }
}