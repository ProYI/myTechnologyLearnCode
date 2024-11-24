package online.proyi.normal.test.listFlip;

import online.proyi.normal.test.entity.Node;

import java.util.ArrayList;
import java.util.Arrays;

public class LinkedListReverser {
    public Node reverseLinkedList(Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        Node newHead = reverseLinkedList(head.getNext());
        head.getNext().setNext(head);

        head.setNext(null);

        return newHead;
    }

    public static void main(String[] args) {
        LinkedListReverser reverser = new LinkedListReverser();

        Node.print(reverser.reverseLinkedList(LinkedListHelper.createLinkedList(Arrays.asList(1, 3, 5, 6, 7))));
        Node.print(reverser.reverseLinkedList(LinkedListHelper.createLinkedList(Arrays.asList(1))));
        Node.print(reverser.reverseLinkedList(LinkedListHelper.createLinkedList(new ArrayList<>())));
    }
}
