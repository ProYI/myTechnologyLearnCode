package online.proyi.interview.question.singleton;

/**
 * 单例模式 - 双重检查 - 线程安全
 * 推荐使用
 */
public class Singleton6 {
    /**
     * instance = new Singleton6() 的操作不是原子性的
     * 所以加上 volatile避免CPU重排序
     */
    private volatile static Singleton6 instance;

    // 私有构造函数
    public Singleton6() {
    }

    public static Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    // 此处有CPU重排序风险
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
