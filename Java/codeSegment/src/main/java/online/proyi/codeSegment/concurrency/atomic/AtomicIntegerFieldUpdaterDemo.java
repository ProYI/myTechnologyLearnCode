package online.proyi.codeSegment.concurrency.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterDemo {

    // 指定更新某一个类中，某一个字段的值，并且该字段还必须得被 volatile修饰 且不被static 修饰 的值
    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterDemo> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterDemo.class, "count");

    public volatile int count = 100;

    public int getCount() {
        return count;
    }

    private static AtomicIntegerFieldUpdaterDemo demo = new AtomicIntegerFieldUpdaterDemo();

    public static void main(String[] args) {
        if (updater.compareAndSet(demo, 100, 120)) {
            System.out.println("update success 1,  " + demo.getCount());
        }
        if (updater.compareAndSet(demo, 100, 120)) {
            System.out.println("update success 2, " + demo.getCount());
        } else {
            System.out.println("update failed, " + demo.getCount());
        }
    }
}
