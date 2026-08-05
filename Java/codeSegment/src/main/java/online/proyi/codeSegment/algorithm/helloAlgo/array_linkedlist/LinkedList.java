package online.proyi.codeSegment.algorithm.helloAlgo.array_linkedlist;

import org.codehaus.jackson.map.util.LinkedNode;

public class LinkedList {
    public static void main(String[] args) {
        /* 初始化链表 1 -> 3 -> 2 -> 5 -> 4 */
        ListNode n0 = new ListNode(1);
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(5);
        ListNode n4 = new ListNode(4);

        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
    }


    // 插入节点
    /* 在链表的节点 n0 之后插入节点 P */
    void insert(ListNode n0, ListNode p) {
        ListNode n1 = n0.next;
        n0.next = p;
        p.next = n1;
    }

    // 删除节点
    /* 删除链表的节点 n0 之后的首个节点 */
    void remove(ListNode n0) {
        if (n0.next == null) {
            return;
        }
        // n0 -> p -> n1
        ListNode p = n0.next;
        n0.next = p.next;
    }

    // 访问节点
    /* 访问链表中索引为 index 的节点 */
    // 访问链表的第 𝑖 个节点需要循环 𝑖 − 1 轮，时间复杂度为 𝑂(𝑛)
    ListNode access(ListNode head, int index) {
        for (int i = 0; i < index; i++) {
            if (head == null) {
                return null;
            }
            head = head.next;
        }
        return head;
    }

    // 查找节点
    /* 在链表中查找值为 target 的首个节点 */
    int find(ListNode head, int target) {
        int index = 0;
        while (head != null) {
            if (head.val == target) {
                return index;
            }
            head = head.next;
            index++;
        }
        return -1;
    }

}
