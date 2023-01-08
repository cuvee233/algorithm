package com.weiyi.alorithm.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 二叉树的最大宽度
 *
 * @author weiyi
 * @date 2023-01-07
 */
public class WidthOfBtree {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
//        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
//        TreeNode treeNode8 = new TreeNode(8);
//        TreeNode treeNode9 = new TreeNode(9);
//        TreeNode treeNode10 = new TreeNode(10);
//        TreeNode treeNode11 = new TreeNode(11);
//        TreeNode treeNode12 = new TreeNode(12);
//        TreeNode treeNode13 = new TreeNode(13);
//        TreeNode treeNode14 = new TreeNode(14);
//        TreeNode treeNode15 = new TreeNode(15);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

//        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
//
//        treeNode4.left = treeNode8;
//        treeNode4.right = treeNode9;
//
//        treeNode5.left = treeNode10;
//        treeNode5.right = treeNode11;
//
//        treeNode6.left = treeNode12;
//        treeNode6.right = treeNode13;
//
//        treeNode7.left = treeNode14;
//        treeNode7.right = treeNode15;

        WidthOfBtree widthOfBtree = new WidthOfBtree();
        System.out.println(widthOfBtree.widthOfBinaryTree(treeNode1));
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addFirst(root);

        int maxSize = 0;
        while (!queue.isEmpty()) {
            int curWidth = queue.size();
            maxSize = Math.max(maxSize, curWidth);
            while (curWidth-- > 0) {
                TreeNode treeNode = queue.pollLast();
                if (treeNode.left != null) {
                    queue.addFirst(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.addFirst(treeNode.right);
                }
            }
        }

        return maxSize;
    }

}
