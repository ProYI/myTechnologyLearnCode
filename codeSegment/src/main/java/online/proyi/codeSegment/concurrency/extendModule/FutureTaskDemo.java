package online.proyi.codeSegment.concurrency.extendModule;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * main可以获取异步线程执行返回结果
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });

        new Thread(futureTask).start();
        System.out.println("do something in main");
        Thread.sleep(1000);
        String result = futureTask.get();
        System.out.println("result: " + result);
    }
}
