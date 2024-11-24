package online.proyi.normal.test.listFlip;

import online.proyi.normal.test.entity.Node;

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
}
