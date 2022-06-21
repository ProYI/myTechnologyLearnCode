package online.proyi.codeSegment.thread.createthread.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器创建线程, 本质仍然是实现Runable接口
 */
public class TimeTask {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时器创建线程-" + Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}