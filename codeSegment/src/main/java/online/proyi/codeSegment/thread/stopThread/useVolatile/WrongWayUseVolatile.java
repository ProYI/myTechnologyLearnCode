package online.proyi.codeSegment.thread.stopThread.useVolatile;

public class WrongWayUseVolatile implements Runnable {
    private volatile boolean canceled = false;
    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 10000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayUseVolatile r = new WrongWayUseVolatile();
        Thread thread = new Thread(r);
        thread.start();
        Thread.sleep(5000);
        r.canceled = true;

        /**
         * 描述： 演示volatile的局限
         * part1 看似可行 线程在执行5秒后的确中断了运行
         */
    }
}
