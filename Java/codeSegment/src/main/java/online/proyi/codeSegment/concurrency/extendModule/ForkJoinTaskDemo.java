package online.proyi.codeSegment.concurrency.extendModule;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTaskDemo extends RecursiveTask<Integer> {

    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinTaskDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinTaskDemo leftTask = new ForkJoinTaskDemo(start, middle);
            ForkJoinTaskDemo rightTask = new ForkJoinTaskDemo(middle + 1, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待任务执行结束合并其结果
            int leftResult = leftTask.join();
            System.out.println("leftResult : " + leftResult);
            int rightResult = rightTask.join();
            System.out.println("rightResult : " + rightResult);

            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // 生成计算任务， 计算 1+2+3+4
        ForkJoinTaskDemo task = new ForkJoinTaskDemo(1, 100);
        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);

        try {
            System.out.println("result : " + result.get());
        } catch (Exception e) {
            System.out.println("exception " + e);
        }
    }
}
