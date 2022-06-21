package online.proyi.codeSegment.thread.createthread.wrongways;

/**
 * lambda表达式创建线程，本质未发生改变
 */
public class Lambda {
    public static void main(String[] args) {
        /**
         * 本质是 Runnable接口作为参数来创建线程
         */
        new Thread(()-> System.out.println("通过lambda表达式创建线程-" + Thread.currentThread())).start();
    }
}
