package online.proyi.codeSegment.algorithm.helloAlgo.stack_queue;

import online.proyi.codeSegment.algorithm.helloAlgo.array_linkedlist.ListNode;

/**
 * 基于链表实现的队列
 */
public class LinkedListQueue {
    // front:头 rear:尾
    private ListNode front, rear;

    private int queSize = 0;

    public LinkedListQueue() {
        front = rear = null;
    }

    // 获取队列的长度
    public int size() {
        return queSize;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return size() == 0;
    }

    // 入队
    public void push(int num) {
        // 在尾节点后添加 num
        ListNode node = new ListNode(num);

        // 队列为空，则令头、尾节点都指向该节点
        if (front == null) {
            front = rear = node;
        } else {
            // 队列不为空，则将该节点添加到尾节点后
            rear.next = node;
            rear = node;
        }
        queSize++;
    }

    // 访问队首元素
    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return front.val;
    }

    // 出队
    public int pop() {
        int nun = peek();

        // 删除头结点
        front = front.next;
        queSize--;
        return nun;
    }

    // 将链表转化为 Array 并返回
    public int[] toArray() {
        ListNode node = this.front;

        int[] res = new int[size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = node.val;
            node = node.next;
        }
        return res;
    }
}
