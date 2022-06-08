package online.proyi.designPatterns._1_singleton;

/**
 * 单例模式 - 懒汉式 - 线程安全
 * 不推荐使用
 */
public class Singleton4 {
    private static Singleton4 instance;

    // 私有构造函数
    public Singleton4() {
    }

    /**k
     * 使用synchronized可以保证线程安全，但是方法会成为阻塞方法，导致性能问题
     */
    public synchronized static Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
