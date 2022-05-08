package online.proyi.codeSegment.thread.stopThread;

/**
 * 最佳实践
 * catch住InterruptedException之后的优先选择：在方法签名中抛出异常
 * 那么在run()就会强制 try/catch
 */
public class RightWayStopThreadInProd implements Runnable {
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                throwInMethod();
            }
        } catch (InterruptedException e) {
            // 保存日志、停止线程、报警等业务代码
            System.out.println("保存日志");
            e.printStackTrace();
        }
    }

    /**
     * 此处方法不能使用 try/catch 而应该抛出
     * 因为在调用方法中try/catch是低级的异常处理，而且调用方无法感知，无法处理
     */
    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
