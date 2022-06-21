package online.proyi.codeSegment.thread.startUpThread;

public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        runnable.run();
        new Thread(runnable).start();

        /**
         * 对比 start()和run() 两种启动线程的方式
         *  执行结果：
         *  main
         *  Thread-0
         *
         * 说明runnable调用run()时，线程还未启动，仍然是主线程在执行 run()方法内的内容
         * 调用start()方法后，是新线程在执行，所以打印出新线程的名称
         */
    }
}
