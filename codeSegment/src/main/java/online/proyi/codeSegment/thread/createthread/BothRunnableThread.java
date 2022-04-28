package online.proyi.codeSegment.thread.createthread;

/**
 * 同时使用Runnable和Thread两种实现线程的方式
 */
public class BothRunnableThread {
    public static void main(String[] args) {
        /**
         * new Thread创建匿名内部类，即使 传入 runnable对象参数
         * 可是 Thread的run方法重写，覆盖掉了 runable的run方法
         * 所以只会打印 Thread的run方法内容
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("来自Runnable的run方法内容");
            }
        }) {
            @Override
            public void run() {
                System.out.println("继承Thread类重写run方法内容");
            }
        }.start();
    }
}
