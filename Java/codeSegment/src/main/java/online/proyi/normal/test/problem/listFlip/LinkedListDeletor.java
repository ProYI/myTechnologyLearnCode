package online.proyi.normal.test.problem.listFlip;

import online.proyi.normal.test.problem.entity.Node;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 删除链表中的元素
 *
 * 1 -> 2 -> 3 -> 2 -> 5 -> 6
 */
public class LinkedListDeletor {
    public Node deleteIfEquals(Node head, int value) {
        // 如果头节点 有多个满足的值 使用while一直处理
        while (head != null && head.getValue() == value) {
            head = head.getNext();
        }

        // 处理完head满足情况，可能为null
        if (head == null) {
            return null;
        }

        Node prev = head;

        while (prev.getNext() != null) {

            if (prev.getNext().getValue() == value) {
                prev.setNext(prev.getNext().getNext());
            } else {
                prev = prev.getNext();
            }
        }
        return head;
    }

    public static void main(String[] args) {
        LinkedListDeletor deletor = new LinkedListDeletor();
        Node.print(deletor.deleteIfEquals(LinkedListHelper.createLinkedList(Arrays.asList(1, 2, 3, 2, 5, 6)), 2));
        Node.print(deletor.deleteIfEquals(LinkedListHelper.createLinkedList(new ArrayList<>()), 2));
        Node.print(deletor.deleteIfEquals(LinkedListHelper.createLinkedList(Arrays.asList(1, 2, 3, 2, 2)), 2));
        Node.print(deletor.deleteIfEquals(LinkedListHelper.createLinkedList(Arrays.asList(1, 2, 3, 2, 2)), 1));
        Node.print(deletor.deleteIfEquals(LinkedListHelper.createLinkedList(Arrays.asList(2, 2, 3, 2, 2)), 2));
        Node.print(deletor.deleteIfEquals(LinkedListHelper.createLinkedList(Arrays.asList(2, 2, 2, 2, 2)), 2));
        Node.print(deletor.deleteIfEquals(LinkedListHelper.createLinkedList(Arrays.asList(2)), 1));
    }
}
