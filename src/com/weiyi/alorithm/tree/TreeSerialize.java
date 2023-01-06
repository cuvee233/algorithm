package com.weiyi.alorithm.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 树的序列化与反序列化
 *
 * @author weiyi
 * @date 2023-01-06
 */
public class TreeSerialize {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode10 = new TreeNode(10);
        TreeNode treeNode11 = new TreeNode(11);
        TreeNode treeNode12 = new TreeNode(12);
        TreeNode treeNode13 = new TreeNode(13);
        TreeNode treeNode14 = new TreeNode(14);
        TreeNode treeNode15 = new TreeNode(15);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
//
//        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left = treeNode6;
//        treeNode3.right = treeNode7;

        treeNode4.left = treeNode8;
        treeNode4.right = treeNode9;

        treeNode5.left = treeNode10;
        treeNode5.right = treeNode11;

        treeNode6.left = treeNode12;
        treeNode6.right = treeNode13;

//        treeNode7.left = treeNode14;
//        treeNode7.right = treeNode15;

        TreeSerialize treeSerialize = new TreeSerialize();
        String serialize = treeSerialize.serialize(treeNode1);
        System.out.println(serialize);

        TreeNode root = treeSerialize.deserialize(serialize);
        System.out.println();

    }

    /**
     * 层序
     *
     * @param root 头节点
     * @return 序列化之后的字符串, 逗号分割, 空节点为#
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.val);

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollLast();

            if (node.left != null) {
                queue.addFirst(node.left);
                sb.append(",").append(node.left.val);
            } else {
                sb.append(",").append("#");
            }

            if (node.right != null) {
                queue.addFirst(node.right);
                sb.append(",").append(node.right.val);
            } else {
                sb.append(",").append("#");
            }
        }

        return sb.toString();
    }

    /**
     * 反序列化
     * 1,2,3,#,5,6,#,10,11,12,13,#,#,#,#,#,#,#,#
     *
     * @param data 反序列化字符串
     * @return 头节点
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }

        String[] treeNodeArr = data.split(",");

        Deque<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = buildNode(treeNodeArr[0]);
        queue.addFirst(root);

        int cur = 0;
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.pollLast();
            TreeNode left = buildNode(treeNodeArr[++cur]);
            TreeNode right = buildNode(treeNodeArr[++cur]);
            curNode.left = left;
            curNode.right = right;

            if (left != null) {
                queue.addFirst(left);
            }

            if (right != null) {
                queue.addFirst(right);
            }
        }

        return root;
    }

    public TreeNode buildNode(String val) {
        if ("#".equals(val)) {
            return null;
        }
        return new TreeNode(Integer.parseInt(val));
    }

}
