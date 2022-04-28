package online.proyi.codeSegment.thread.createthread;

/**
 * 使用实现Runnable接口的方式实现线程
 */
public class RunnableStyle implements Runnable {
    @Override
    public void run() {
        System.out.println("使用实现Runnable接口的方式实现线程");
    }

    public static void main(String[] args) {
        new Thread(new RunnableStyle()).start();
    }
}
