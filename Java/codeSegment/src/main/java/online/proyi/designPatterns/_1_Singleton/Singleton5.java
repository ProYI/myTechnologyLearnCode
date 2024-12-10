package online.proyi.designPatterns._1_Singleton;

/**
 * 单例模式 - 懒汉式 - 线程不安全
 * 不推荐使用
 */
public class Singleton5 {
    private static Singleton5 instance;

    // 私有构造函数
    private Singleton5() {
    }

    public static Singleton5 getInstance() {
        if (instance == null) {
            /**
             * 线程不安全的原因：
             * 线程 A、B在运行时，第一次运行， instance为null,均成功运行到if语句内
             * 因为synchronized锁只能被一个线程获取，所以假设A获取到锁，B线程即在等待状态
             * 下一步 A创建对象，释放锁
             * B线程获取到锁，此时不再判断instance是否为null，直接执行 instance = new Singleton5()
             * 此时B线程也创建了一个Singleton5对象，破坏了单例
             */
            synchronized (Singleton5.class) {
                instance = new Singleton5();
            }
        }
        return instance;
    }
}
