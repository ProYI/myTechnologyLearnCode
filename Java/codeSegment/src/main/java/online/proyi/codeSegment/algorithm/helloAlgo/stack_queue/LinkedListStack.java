package online.proyi.codeSegment.algorithm.helloAlgo.stack_queue;

import online.proyi.codeSegment.algorithm.helloAlgo.array_linkedlist.ListNode;

/**
 * 基于链表实现栈
 */
public class LinkedListStack {
    /*
    头结点 栈顶
    只是一个引用变量（指针），不是节点
    - 本身不存数据（没有 `val`）
    - 本身没有 `next` 指针
    - 只是一个"箭头"，指向当前的栈顶节点
    */
    private ListNode stackPeek;
    private int stkSize = 0;

    public LinkedListStack() {
        this.stackPeek = null;
    }

    // 获取栈长度
    public int size() {
        return stkSize;
    }

    // 判断是否为空
    public boolean isEmpty() {
        return stkSize == 0;
    }

    // 入栈
    /*
    push(1) 之后: stackPeek ───→ [ListNode{val=1, next=null}]
    push(2) 之后: stackPeek ───→ [ListNode{val=2, next=──→ ListNode{val=1, next=null}}]
    push(3) 之后: stackPeek ───→ [ListNode{val=3, next=──→ ListNode{val=2, next=──→ ListNode{val=1, next=null}}}]
    */
    public void push(int num) {
        ListNode newNode = new ListNode(num);
        newNode.next = stackPeek;
        stackPeek = newNode;
        stkSize++;
    }

    // 访问栈顶元素
    public int peek() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        return stackPeek.val;
    }

    // 出栈
    public int pop() {
        int num = peek();
        stackPeek = stackPeek.next;
        stkSize--;
        return num;
    }

    /* 将 List 转化为 Array 并返回 */
    public int[] toArray() {
        ListNode node = stackPeek;
        int[] res = new int[size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = node.val;
            node = node.next;
        }
        return res;
    }
}
