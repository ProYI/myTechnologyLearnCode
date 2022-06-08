package online.proyi.designPatterns._1_singleton;

/**
 * 单例模式 - 饿汉式 - 线程安全
 * 静态代码块
 */
public class Singleton3 {
    public Singleton3() {
    }

    private final static Singleton3 instance;

    static {
        instance = new Singleton3();
    }


    // 静态工厂方法
    public static Singleton3 getInstance() {
        return instance;
    }
}
