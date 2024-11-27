package online.proyi.normal.test.problem.tree;

import online.proyi.normal.test.problem.entity.TreeNode;

public class TreeTraversal {

    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.getValue());
        preOrder(root.getLeftNode());
        preOrder(root.getRightNode());
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.getLeftNode());
        System.out.print(root.getValue());
        inOrder(root.getRightNode());
    }

    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.getLeftNode());
        postOrder(root.getRightNode());
        System.out.print(root.getValue());
    }

    public static void main(String[] args) {
        TreeCreator creator = new TreeCreator();
        TreeTraversal treeTraversal = new TreeTraversal();
        TreeNode simpleTree = creator.createSimpleTree();

        // 前序遍历
        treeTraversal.preOrder(simpleTree);
        System.out.println();

        // 中序遍历
        treeTraversal.inOrder(simpleTree);
        System.out.println();

        // 后序遍历
        treeTraversal.postOrder(simpleTree);
        System.out.println();

        System.out.println("======");

        TreeNode tree = creator.createTree("ACDEFHGB", "DECAHFBG");
        treeTraversal.postOrder(tree);
        System.out.println();

        TreeNode tree2 = creator.createTree("", "");
        treeTraversal.postOrder(tree2);
        System.out.println();

        TreeNode tree3 = creator.createTree("A", "A");
        treeTraversal.postOrder(tree3);
        System.out.println();

        TreeNode tree4 = creator.createTree("AB", "BA");
        treeTraversal.postOrder(tree4);
        System.out.println();

        System.out.println("======");


        System.out.println(creator.postOrder("ACDEFHGB", "DECAHFBG"));
        System.out.println(creator.postOrder("", ""));
        System.out.println(creator.postOrder("A", "A"));
        System.out.println(creator.postOrder("AB", "BA"));
    }
}
