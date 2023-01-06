package com.weiyi.alorithm.tree;

import com.weiyi.leetcode.tree.multi.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * n叉树转b树
 * 思路:二叉树的左子节点和其右边界为直接孩子节点
 *
 * @author weiyi
 * @date 2023-01-06
 */
public class NtreeToBTree {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);

        node1.children.add(node2);
        node1.children.add(node3);
        node1.children.add(node4);
        node1.children.add(node5);


        node2.children.add(node6);
        node2.children.add(node7);
        node2.children.add(node8);

        node4.children.add(node9);
        node4.children.add(node10);


        NtreeToBTree ntreeToBTree = new NtreeToBTree();
        TreeNode treeNode = ntreeToBTree.encode(node1);

        Node decode = ntreeToBTree.decode(treeNode);
        System.out.println();
    }

    public TreeNode encode(Node node) {
        if (node == null) {
            return null;
        }

        TreeNode treeNode = nodeToTreeNode(node);

        List<Node> children = node.children;
        if (children != null && children.size() > 0) {
            TreeNode head = null;
            for (Node child : children) {

                // 先搞定孩子节点
                TreeNode encodeNode = encode(child);

                // 再搞定兄弟节点
                if (head == null) {
                    // 第一个子节点为二叉树的左子节点
                    head = encodeNode;
                    treeNode.left = head;
                } else {
                    // 其它子节点为左子节点的右节点
                    head.right = encodeNode;
                    head = head.right;
                }
            }
        }

        return treeNode;
    }

    public TreeNode nodeToTreeNode(Node node) {
        TreeNode treeNode = new TreeNode();
        treeNode.val = node.val;
        return treeNode;
    }


    public Node decode(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }


        return new Node(treeNode.val, getChildList(treeNode.left));
    }

    public List<Node> getChildList(TreeNode left) {
        List<Node> childList = new ArrayList<>();
        while (left != null) {
            // 先递归当前节点的孩子节点
            Node leftNode = new Node(left.val, getChildList(left.left));
            childList.add(leftNode);
            left = left.right;
        }
        return childList;
    }

}
