package online.proyi.normal.test.problem.listFlip;

import online.proyi.normal.test.problem.entity.Node;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 链表翻转（非递归）
 *
 * 因为递归翻转时，需要使用栈来存储递归层级，在大数据量情况下会造成栈溢出
 *
 * 1 -> 2 -> 3 -> 4 -> 5 -> null
 *
 * null <- 1 <- 2 <- 3 <- 4 <- 5
 *
 */
public class LinkedListReverser2 {
    public Node reverseLinkedList(Node head) {
        Node newHead = null;
        Node currentHead = head;

        while (currentHead != null) {
            Node next = currentHead.getNext();
            currentHead.setNext(newHead);

            newHead = currentHead;
            currentHead = next;
        }

        return newHead;
    }

    public static void main(String[] args) {
        LinkedListReverser2 reverser = new LinkedListReverser2();

        Node.print(reverser.reverseLinkedList(LinkedListHelper.createLinkedList(Arrays.asList(1, 3, 5, 6, 7))));
        Node.print(reverser.reverseLinkedList(LinkedListHelper.createLinkedList(Arrays.asList(1))));
        Node.print(reverser.reverseLinkedList(LinkedListHelper.createLinkedList(new ArrayList<>())));
        System.out.println("==========");
        Node.print(reverser.reverseLinkedList(LinkedListHelper.createLargeLinkedList(10000)));
    }
}
