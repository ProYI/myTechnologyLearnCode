package online.proyi.normal.test.problem.tree;

import online.proyi.normal.test.problem.entity.TreeNode;

public class TreeCreator {

    public TreeNode createSimpleTree() {
        TreeNode rootNode = new TreeNode('A');
        rootNode.setLeftNode(new TreeNode('B'));
        rootNode.setRightNode(new TreeNode('C'));
        rootNode.getLeftNode().setLeftNode(new TreeNode('D'));
        rootNode.getLeftNode().setRightNode(new TreeNode('E'));
        rootNode.getLeftNode().getRightNode().setLeftNode(new TreeNode('G'));
        rootNode.getRightNode().setRightNode(new TreeNode('F'));

        return rootNode;
    }

    public TreeNode createTree(String preOrder, String inOrder) {
        if (preOrder.isEmpty()) {
            return null;
        }
        char rootValue = preOrder.charAt(0);
        int rootIndex = inOrder.indexOf(rootValue);

        TreeNode rootNode = new TreeNode(rootValue);
        rootNode.setLeftNode(createTree(
                preOrder.substring(1, 1 + rootIndex),
                inOrder.substring(0, rootIndex)));

        rootNode.setRightNode(createTree(
                preOrder.substring(1 + rootIndex),
                inOrder.substring(1 + rootIndex)));

        return rootNode;
    }

    public String postOrder(String preOrder, String inOrder) {
        if (preOrder.isEmpty()) {
            return "";
        }
        char rootValue = preOrder.charAt(0);
        int rootIndex = inOrder.indexOf(rootValue);

        // 后序遍历 = 左子树 + 右子树 + 根
        return postOrder(preOrder.substring(1, 1 + rootIndex), inOrder.substring(0, rootIndex)) +
                postOrder(preOrder.substring(1 + rootIndex), inOrder.substring(1 + rootIndex)) +
                rootValue
        ;
    }
}
