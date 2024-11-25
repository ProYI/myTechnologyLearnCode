package online.proyi.normal.test.problem.entity;

public class Node {
    private final int value;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    private Node next;

    public int getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public static void print(Node node) {
        while (node != null) {
            System.out.print(node.getValue());
            System.out.print(" ");
            node = node.getNext();
        }
        System.out.println("");
    }
}
