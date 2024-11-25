package online.proyi.normal.test.problem.listFlip;

import online.proyi.normal.test.problem.entity.Node;

import java.util.List;

public class LinkedListHelper {
    public static Node createLinkedList(List<Integer> data) {
        if (data.isEmpty()) {
            return null;
        }
        Node firstNode = new Node(data.get(0));

        Node next = createLinkedList(data.subList(1, data.size()));
        firstNode.setNext(next);
        return firstNode;
    }

    public static Node createLargeLinkedList(int size) {
        Node prev = null;
        Node head = null;

        for (int i = 0; i < size; i++) {
            Node node = new Node(i);
            if (prev != null) {
                prev.setNext(node);
            } else {
                head = node;
            }
            prev = node;
        }

        return head;
    }
}
