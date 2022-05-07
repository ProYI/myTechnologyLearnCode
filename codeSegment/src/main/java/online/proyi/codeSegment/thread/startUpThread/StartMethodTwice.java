package online.proyi.codeSegment.thread.startUpThread;

/**
 * 调用两次start()方法会发生什么？
 */
public class StartMethodTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
        /**
         * Exception in thread "main" java.lang.IllegalThreadStateException 非法的线程状态
         * 抛出异常
         *
         * start()方法源码调用内容：
         * 启动新线程时检查线程状态，加入线程组，调用start0()本地方法
         */
    }
}
