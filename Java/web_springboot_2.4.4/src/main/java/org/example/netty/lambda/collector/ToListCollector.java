package org.example.netty.lambda.collector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 自行实现收集器接口
 *
 * public interface Collector<T, A, R> {
 *  Supplier<A> supplier();
 *  BiConsumer<A, T> accumulator();
 *  Function<A, R> finisher();
 *  BinaryOperator<A> combiner();
 *  Set<Characteristics> characteristics();
 * }
 *
 * T是流中要收集的项目的泛型
 * A是累加器的类型，累加器是在收集过程中用于累积部分结果的对象
 * R是收集操作得到的对象（通常但并不一定是集合）的类型
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    /**
     * 建立新的结果容器
     * supplier方法必须返回一个结果为空的Supplier，也就是一个无参数函数，在调用时它会创建一个空的累加器实例，供数据收集过程使用
      */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new; // 创建集合操作起点
    }

    /**
     *  将元素添加到结果容器
     *
     * accumulator方法会返回执行归约操作的函数
     * 当遍历到流中第n个元素时，这个函数执行时会有两个参数：保存归约结果的累加器（已收集了流中的前 n1 个项目），还有第n个元素本身
     * 该函数将返回void,因为累加器是原位更新，即函数的执行改变了它的内部状态以体现遍历的元素的效果
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add; // 累积遍历过的项目，原位修改累加器
    }

    /**
     * 合并两个结果容器
     * combiner方法会返回一个供归约操作使用的函数，它定义了对流的各个子部分进行并行处理时，各个子部分归约所得的累加器要如何合并
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        // 修改第一个累加器，将第二个合并到第一个
        return (list1, list2) -> {list1.addAll(list2); return list1;};
    }

    /**
     *  对结果容器应用最终转换
     * 在遍历完流后，finisher方法必须返回在累积过程的最后要调用的一个函数，以便将累加器对象转换为整个集合操作的最终结果
     * 通常累加器对象恰好符合预期的最终结果，因此无需进行转换。所以finisher方法只需返回identity函数
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        // 恒等函数
        return Function.identity();
    }

    /**
     * characteristics会返回一个不可变的Characteristics集合
     * 它定义了收集器的行为——尤其是关于流是否可以并行归约，以及可以使用哪些优化的提示
     *
     * Characteristics是包含三个项目的枚举
     *
     * UNORDERED——归约结果不受流中项目的遍历和累积顺序的影响
     * CONCURRENT——accumulator函数可以从多个线程同时调用，且该收集器可以并行归约流。如果收集器没有标为UNORDERED，那它仅在用于无序数据源时才可以并行归约
     * IDENTITY_FINISH——这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种情况下，累加器对象将会直接用作归约过程的最终结果。这也意味着，将累加器A不加检查地转换为结果R是安全的。
     */
    @Override
    public Set<Characteristics> characteristics() {
        // 为收集器添加 IDENTITY_FINISH 和 CONCURRENT 状态
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
