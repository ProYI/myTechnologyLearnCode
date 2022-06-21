package online.proyi.codeSegment.thread.stopThread;

/**
 * 带有sleep的中断线程的写法
 */
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();;
        Thread.sleep(500);
        thread.interrupt();
        /**
         * 执行结果：打印 java.lang.InterruptedException: sleep interrupted
         *
         * 1.当程序带有sleep或者类似能让线程进入阻塞方法的时候
         * 在线程使用sleep等方法会使线程进入阻塞状态时，必然要求将对应的InterruptedException处理掉。
         *
         * 2.即使使用异常捕获后，当线程进入阻塞过程中依然可以响应中断状态
         */
    }
}
