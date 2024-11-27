package online.proyi.normal.test.problem.entity;

public class TreeNode {
    private final char value;

    private TreeNode leftNode;

    private TreeNode rightNode;

    public TreeNode(char value) {
        this.value = value;
        leftNode = null;
        rightNode = null;
    }

    public char getValue() {
        return value;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }
}
