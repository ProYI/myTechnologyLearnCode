package online.proyi.codeSegment.algorithm.helloAlgo.stack_queue.double_ended_queue;

/**
 * 基于双向链表实现的双向队列
 */

// 双向链表节点
class ListNode {
    // 节点值
    int val;
    // 后继节点引用
    ListNode next;
    // 前驱节点引用
    ListNode prev;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
        this.prev = null;
    }
}


public class LinkedListDeque {

    private ListNode front, rear;

    private int queSize;

    public LinkedListDeque() {
        front = rear = null;
    }

    // 获取双向队列的长度
    public int size() {
        return queSize;
    }

    // 判断双向队列是否为空
    public boolean isEmpty() {
        return size() == 0;
    }

    // 入队
    public void push(int num, boolean isFront) {
        ListNode node = new ListNode(num);

        // 若链表为空，则令 front 和 rear 都指向 node
        if (isEmpty()) {
            front = rear = node;
        } else if (isFront) {
            // 队首入队
            // 将 node 添加至链表头部
            front.prev = node;
            node.next = front;
            front = node;
        } else {
            // 队尾入队
            // 将 node 添加至链表尾部
            rear.next = node;
            node.prev = rear;
            rear = node;
        }
        queSize++;
    }

    // 队首入队
    public void pushFirst(int num) {
        push(num, true);
    }

    // 队尾入队
    public void pushLast(int num) {
        push(num, false);
    }

    // 出队
    public int pop(boolean isFront) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        int val;
        if (isFront) {
            val = front.val;

            // 删除头节点
            ListNode fNext = front.next;
            if (fNext != null) {
                fNext.prev = null;
                front.next = null;
            }
            front = fNext;
        } else {
            val = rear.val;
            ListNode rPrev = rear.prev;
            if (rPrev != null) {
                rPrev.next = null;
                rear.prev = null;
            }
            rear = rPrev;
        }
        queSize--;
        return val;
    }

    // 队首出队
    public int popFirst() {
        return pop(true);
    }

    // 队尾出队
    public int popLast() {
        return pop(false);
    }

    // 访问队首元素
    public int peekFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return front.val;
    }

    // 访问队尾元素
    public int peekLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return rear.val;
    }

    // 返回数组
    public int[] toArray() {
        ListNode node = front;
        int[] res = new int[size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = node.val;
            node = node.next;
        }
        return res;
    }
}

/**
 * 基于环形数组实现的双向队列
 */
class ArrayDeque {
    private int[] nums;
    private int front;
    private int queSize;

    public ArrayDeque(int capacity) {
        nums = new int[capacity];
        front = 0;
        queSize = 0;
    }

    // 获取双向队列的容量
    public int capacity() {
        return nums.length;
    }

    // 获取双向队列的长度
    public int size() {
        return queSize;
    }

    // 判断双向队列是否为空
    public boolean isEmpty() {
        return size() == 0;
    }

    // 计算环形数组索引
    public int index(int i) {
        // 通过取余操作实现数组首尾相连
        // 当 i 越过数组尾部后，回到头部
        // 当 i 越过数组头部后，回到尾部
        return (i + capacity()) % capacity();
    }

    // 队首入列
    public void pushFirst(int num) {
        if (size() == capacity()) {
            System.out.println("双向队列已满");
            return;
        }
        // 队首指针向左移动一位
        // 通过取余操作实现 front 越过数组头部后回到尾部
        front = index(front - 1);
        // 将 num 添加至队首
        nums[front] = num;
        queSize++;
    }

    // 队尾入队
    public void pushLast(int num) {
        if (size() == capacity()) {
            System.out.println("双向队列已满");
            return;
        }
        int rear = index(front + queSize);

        // 将 num 添加至队尾
        nums[rear] = num;
        queSize++;
    }

    // 访问队首元素
    public int peekFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return nums[front];
    }

    // 访问队尾元素
    public int peekLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        // 计算尾元素索引
        int last = index(front + queSize - 1);
        return nums[last];
    }

    // 队首出队
    public int popFirst() {
        int num = nums[front];

        front = index(front + 1);
        queSize--;
        return num;
    }

    // 队尾出队
    public int popLast() {
        int num = peekLast();
        queSize--;
        return num;
    }

    // 返回数组
    public int[] toArray() {
        // 仅转换有效长度范围内的列表元素
        int[] res = new int[size()];
        for (int i = 0, j = front; i < res.length; i++, j++) {
            res[i] = nums[index(j)];
        }
        return res;
    }
}
