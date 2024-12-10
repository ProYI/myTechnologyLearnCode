package online.proyi.designPatterns._1_Singleton;

/**
 * 单例模式 - 懒汉式 - 线程不安全
 */
public class Singleton1 {
    private static Singleton1 instance;

    // 私有构造函数
    private Singleton1() {
    }

    // 静态工厂方法
    public static Singleton1 getInstance() {
        if (instance == null) {
            // 若两个线程同时进入此方法时，判断为null, 同时new instance对象，会出现多个instance对象
            instance = new Singleton1();
        }
        return instance;
    }
}
