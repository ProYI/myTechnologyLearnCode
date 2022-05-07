package online.proyi.codeSegment.thread.createthread.wrongways;

/**
 * 匿名内部类的方式创建线程 和基础两种创建线程的方式本质并没有区别
 */
public class AnonymousInnerClass {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println("重写run方法-" + Thread.currentThread());
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("重写Runnable接口中run方法-" + Thread.currentThread());
            }
        }).start();
    }
}
