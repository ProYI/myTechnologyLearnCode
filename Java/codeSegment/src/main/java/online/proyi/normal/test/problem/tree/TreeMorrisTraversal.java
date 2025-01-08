package online.proyi.normal.test.problem.tree;

import online.proyi.normal.test.problem.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 不使用递归且不依赖额外空间（如栈或队列）来遍历二叉树，最常用的方法是 莫里斯遍历（Morris Traversal）
 *
 * 通过树的结构和空闲指针来模拟栈的功能。
 * 具体的做法是，在遍历过程中，利用二叉树的右子树空闲指针来存储返回的路径，从而避免了栈的使用。
 *
 *
 */
public class TreeMorrisTraversal {

    // 中序遍历
    public void inOrder(TreeNode root) {
        TreeNode current = root;

        while (current != null) {
            // 如果当前节点的左子树为空，直接访问该节点
            if (current.getLeftNode() == null) {
                System.out.print(current.getValue() + " "); // 访问节点
                current = current.getRightNode(); // 向右移动
            } else {
                // 找到当前节点左子树的最右节点
                TreeNode predecessor = current.getLeftNode();
                while (predecessor.getRightNode() != null && predecessor.getRightNode() != current) {
                    predecessor = predecessor.getRightNode();
                }

                // 如果最右节点的右指针为空，说明还没有被标记过
                if (predecessor.getRightNode() == null) {
                    predecessor.setRightNode(current); // 让当前节点成为其左子树最右节点的右指针
                    current = current.getLeftNode(); // 移动到左子树
                } else {
                    // 如果最右节点的右指针已经指向当前节点，说明左子树已经遍历完成
                    predecessor.setRightNode(null); // 恢复右指针
                    System.out.print(current.getValue() + " "); // 访问节点
                    current = current.getRightNode(); // 向右移动
                }
            }
        }
    }

    // 前序遍历
    public void preOrder(TreeNode root) {
        TreeNode current = root;

        while (current != null) {
            // 如果当前节点没有左子树，访问该节点并移动到右子树
            if (current.getLeftNode() == null) {
                System.out.print(current.getValue() + " "); // 访问节点
                current = current.getRightNode(); // 向右移动
            } else {
                // 找到当前节点左子树的最右节点
                TreeNode predecessor = current.getLeftNode();
                while (predecessor.getRightNode() != null && predecessor.getRightNode() != current) {
                    predecessor = predecessor.getRightNode();
                }

                // 如果最右节点的右指针为空，说明还没有被标记过
                if (predecessor.getRightNode() == null) {
                    predecessor.setRightNode(current); // 将当前节点连接到左子树最右节点的右指针
                    System.out.print(current.getValue() + " "); // 访问节点
                    current = current.getLeftNode(); // 移动到左子树
                } else {
                    // 如果最右节点的右指针已经指向当前节点，说明左子树已经遍历完成
                    predecessor.setRightNode(null); // 恢复右指针
                    current = current.getRightNode(); // 向右移动
                }
            }
        }
    }

    /**
     * 后序遍历
     *
     * 1. 在当前节点的左子树上进行中序遍历，然后通过最右节点的右指针回到当前节点
     * 访问完左子树后，再访问右子树。
     * 由于后序遍历需要最后访问当前节点，所以需要在访问完左子树和右子树后，逆向回去访问当前节点
     */
    public void postOrder(TreeNode root) {
        TreeNode dummy = new TreeNode( '0');  // 新建一个虚拟节点，作为树的根
        dummy.setLeftNode(root);
        TreeNode current = dummy;

        while (current != null) {
            // 如果当前节点没有左子树，直接向右移动
            if (current.getLeftNode() == null) {
                current = current.getRightNode();
            } else {
                // 找到当前节点左子树的最右节点
                TreeNode predecessor = current.getLeftNode();
                while (predecessor.getRightNode() != null && predecessor.getRightNode() != current) {
                    predecessor = predecessor.getRightNode();
                }

                // 如果最右节点的右指针为空，说明还没有被标记过
                if (predecessor.getRightNode() == null) {
                    predecessor.setRightNode(current); // 将当前节点连接到左子树最右节点的右指针
                    current = current.getLeftNode(); // 向左子树移动
                } else {
                    // 如果最右节点的右指针已经指向当前节点，说明左子树已经遍历完成
                    predecessor.setRightNode(null); // 恢复右指针
                    printReverse(current.getLeftNode()); // 逆序打印当前节点的左子树
                    current = current.getRightNode(); // 向右子树移动
                }
            }
        }
    }

    // 辅助方法：逆序打印左子树的路径
    private void printReverse(TreeNode node) {
        TreeNode current = node;
        List<Character> nodes = new ArrayList<>();
        while (current != null) {
            nodes.add(current.getValue());
            current = current.getRightNode();
        }
        // 输出逆序的结果
        for (int i = nodes.size() - 1; i >= 0; i--) {
            System.out.print(nodes.get(i) + " ");
        }
    }

    public static void main(String[] args) {
        TreeCreator creator = new TreeCreator();
        TreeMorrisTraversal TreeMorrisTraversal = new TreeMorrisTraversal();
        TreeNode simpleTree = creator.createSimpleTree();

        // 前序遍历
        TreeMorrisTraversal.preOrder(simpleTree);
        System.out.println();

        // 中序遍历
        TreeMorrisTraversal.inOrder(simpleTree);
        System.out.println();

        // 后序遍历
        TreeMorrisTraversal.postOrder(simpleTree);
        System.out.println();

        System.out.println("======");

        TreeNode tree = creator.createTree("ACDEFHGB", "DECAHFBG");
        TreeMorrisTraversal.postOrder(tree);
        System.out.println();

        TreeNode tree2 = creator.createTree("", "");
        TreeMorrisTraversal.postOrder(tree2);
        System.out.println();

        TreeNode tree3 = creator.createTree("A", "A");
        TreeMorrisTraversal.postOrder(tree3);
        System.out.println();

        TreeNode tree4 = creator.createTree("AB", "BA");
        TreeMorrisTraversal.postOrder(tree4);
        System.out.println();

        System.out.println("======");


        System.out.println(creator.postOrder("ACDEFHGB", "DECAHFBG"));
        System.out.println(creator.postOrder("", ""));
        System.out.println(creator.postOrder("A", "A"));
        System.out.println(creator.postOrder("AB", "BA"));
    }
}
