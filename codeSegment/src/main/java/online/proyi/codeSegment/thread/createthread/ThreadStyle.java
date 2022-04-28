package online.proyi.codeSegment.thread.createthread;

/**
 * 描述： 使用继承Thread实现线程
 */
public class ThreadStyle extends Thread {
    @Override
    public void run() {
        System.out.println("使用继承Thread实现线程");
    }

    public static void main(String[] args) {
        new ThreadStyle().start();
    }
}
