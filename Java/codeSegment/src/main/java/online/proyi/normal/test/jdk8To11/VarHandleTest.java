package online.proyi.normal.test.jdk8To11;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * 变量句柄
 *
 * 变量句柄是一个变量或一组变量的引用，包括静态域，非静态域，数组元素和堆外数据结构中的组成部分等
 * 可以使用类 java.lang.invoke.MethodHandles.Lookup 中的静态工厂方法来创建 VarHandle 对象。通过变量句柄，可以在变量上进行各种操作。这些操作称为访问模式
 *
 * 不同的访问模式尤其在内存排序上的不同语义。
 * 目前一共有 31 种 访问模式，而每种访问模式都 在 VarHandle 中 有对应的方法。这些方法可以对变量进行读取、写入、原子更新、数值原子更新和比特位原子操作等
 *
 */
public class VarHandleTest {
    public static void main(String[] args) throws Exception {
        HandleTarget handleTarget = new HandleTarget();
        VarHandle varHandle = MethodHandles.lookup().findVarHandle(HandleTarget.class, "count", int.class);

        System.out.println(varHandle.get(handleTarget));
        System.out.println(varHandle.getVolatile(handleTarget));
        System.out.println(varHandle.getOpaque(handleTarget));
        System.out.println(varHandle.getAcquire(handleTarget));

    }
}
class HandleTarget {
    public int count = 1;
}