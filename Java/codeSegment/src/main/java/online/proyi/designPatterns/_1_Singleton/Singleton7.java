package online.proyi.designPatterns._1_Singleton;

/**
 * 单例模式 - 静态内部类 - 线程安全
 * 可以使用
 */
public class Singleton7 {

    // 私有构造函数
    private Singleton7() {
    }

    /**
     * 静态内部类不会随着外部类实例化而实例化，只有调用时才实例化，所以实质还是懒加载
     */
    private static class SingletonInstance {
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
