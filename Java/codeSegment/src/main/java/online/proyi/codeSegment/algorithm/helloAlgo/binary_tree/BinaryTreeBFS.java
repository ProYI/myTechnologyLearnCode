package online.proyi.codeSegment.algorithm.helloAlgo.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeBFS {
    public static void main(String[] args) {
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

        List<Integer> data = levelOrder(n1);
        System.out.println(data);
    }

    /* 层序遍历 */
    public static List<Integer> levelOrder(TreeNode root) {
        // 初始化队列，加入根节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 初始化一个列表，用于保存遍历序列
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            // 队列出队
            TreeNode node = queue.poll();
            // 保存节点值
            list.add(node.val);
            if (node.left != null)
                // 左子节点入队
                queue.offer(node.left);
            if (node.right != null)
                // 右子节点入队
                queue.offer(node.right);
        }
        return list;
    }
}
