package online.proyi.normal.test.problem.listFlip;

import online.proyi.normal.test.problem.entity.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedList {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        Node.print(LinkedListHelper.createLinkedList(Arrays.asList(1, 3, 5, 6, 7)));
        Node.print(LinkedListHelper.createLinkedList(List.of(1)));
        Node.print(LinkedListHelper.createLinkedList(new ArrayList<>()));

    }
}

