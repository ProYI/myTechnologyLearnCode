package online.proyi.designPatterns._1_singleton;

/**
 * 单例模式 - 饿汉式 - 线程安全
 * 静态常量
 */
public class Singleton2 {
    private final static Singleton2 instance = new Singleton2();

    // 私有构造函数
    private Singleton2() {
    }

    // 静态工厂方法
    public static Singleton2 getInstance() {
        return instance;
    }
}
