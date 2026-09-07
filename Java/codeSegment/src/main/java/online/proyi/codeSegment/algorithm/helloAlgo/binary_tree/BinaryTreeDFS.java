package online.proyi.codeSegment.algorithm.helloAlgo.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeDFS {
    static void main(String[] args) {
        // 初始化节点
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        // 构建节点之间的引用（指针）
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        preOrder(n1, list1);
        inOrder(n1, list2);
        postOrder(n1, list3);

        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
    }

    /* 前序遍历 */
    static void preOrder(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        // 访问优先级：根节点 -> 左子树 -> 右子树
        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    static void inOrder(TreeNode root, List<Integer> list) {
        if (root == null)
            return;

        // 访问优先级：左子树 -> 根节点 -> 右子树
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    /* 后序遍历 */
    static void postOrder(TreeNode root, List<Integer> list) {
        if (root == null)
            return;

        // 访问优先级：左子树 -> 右子树 -> 根节点
        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.val);
    }
}
