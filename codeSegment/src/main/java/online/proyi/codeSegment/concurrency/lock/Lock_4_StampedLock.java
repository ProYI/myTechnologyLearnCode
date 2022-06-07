package online.proyi.codeSegment.concurrency.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock
 *
 * 控制锁有三种模式：写、读、乐观读
 */
public class Lock_4_StampedLock {


    private double x, y;

    private final StampedLock sl = new StampedLock();

    void move(double deltaX, double deltaY) { // an exclusively locked method
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    // 乐观读锁案例
    double distanceFromOrigin() { // A read-only method
        long stamp = sl.tryOptimisticRead(); // 获取一个乐观读锁
        double currentX = x, currentY = y; // 将两个字段读入本地局部变量
        if (!sl.validate(stamp)) { // 检查发出乐观读锁后同时是否有其他写锁发生？
            stamp = sl.readLock(); // 如果没有，再次获取一个读悲观锁
            try {
                currentX = x; // 将两个字段读入本地局部变量
                currentY = y; // 将两个字段读入本地局部变量
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    // 悲观读锁案例
    void moveIfAtOrigin(double newX, double newY) { // upgrade
        // Could instead start with optimistic, not read mode
        long stamp = sl.readLock();
        try {
            // 循环 检查当前状态是否符合
            while (x == 0.0 && y == 0.0) {
                // 将读锁转换为写锁
                long ws = sl.tryConvertToWriteLock(stamp);
                if (ws != 0L) { // 确认转为写锁是否成功
                    stamp = ws; // 如果成功，替换票据
                    x = newX; // 进行状态改变
                    y = newY; // 进行状态改变
                    break;
                } else { // 如果不能成功转化为写锁
                    sl.unlockRead(stamp); // 显式释放读锁
                    stamp = sl.writeLock(); // 显式直接进行写锁 然后再通过循环在试
                }
            }
        } finally {
            sl.unlock(stamp); // 释放读锁或写锁
        }
    }
}
