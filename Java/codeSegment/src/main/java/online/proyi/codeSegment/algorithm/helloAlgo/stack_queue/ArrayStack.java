package online.proyi.codeSegment.algorithm.helloAlgo.stack_queue;

import java.util.ArrayList;

/**
 * 基于数组实现的栈
 */
public class ArrayStack {
    public ArrayList<Integer> stack;

    public ArrayStack() {
        // 初始化 动态数组
        stack = new ArrayList<>();
    }

    // 获取栈长度
    public int size() {
        return stack.size();
    }

    // 判断栈是否为空
    public boolean isEmpty() {
        return size() == 0;
    }

    // 入栈
    public void push(int num) {
        stack.add(num);
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return stack.remove(stack.size() - 1);
    }

    // 访问栈顶元素
    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return stack.get(stack.size() - 1);
    }

    // 将list转化为Array返回
    public Object[]  toArray() {
        return stack.toArray();
    }
}
