package com.weiyi.alorithm.tree;

import java.util.*;

/**
 * 树的先序、中序、后序遍历，递归版本和非递归实现
 *
 * @author weiyi
 * @date 2023-01-05
 */
public class TreeTraversal {

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

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        treeNode4.left = treeNode8;
        treeNode4.right = treeNode9;

        treeNode5.left = treeNode10;
        treeNode5.right = treeNode11;

        treeNode6.left = treeNode12;
        treeNode6.right = treeNode13;

        treeNode7.left = treeNode14;
        treeNode7.right = treeNode15;


        /**
         *                     1
         *            2                3
         *       4         5       6       7
         *     8    9   10  11  12  13  14  15
         */


        // 先序 中左右 1,2,4,8,9,5,10,11,3,6,12,13,7,14,15
        // 中序 左中右 8,4,9,2,10,5,11,1,12,6,13,3,14,7,15
        // 后序 左右中 8,9,4,10,11,5,2,12,13,6,14,15,7,3,1

        TreeTraversal treeTraversal = new TreeTraversal();
        List<List<Integer>> lists = treeTraversal.levelOrder(treeNode1);
        lists.forEach(System.out::println);
    }


    /**
     * 二叉树层序遍历(利用队列实现)
     * <p>
     * 难点；怎样知道层级，可以每次poll的时候poll出当前队列的长度的元素，然后依次加入这些元素的子节点
     *
     * @param root 头节点
     * @return 层序
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addFirst(root);

        List<List<Integer>> resList = new ArrayList<>();
        while (!queue.isEmpty()) {

            int queueSize = queue.size();

            List<Integer> depthList = new ArrayList<>();
            resList.add(depthList);
            while (queueSize-- > 0) {

                TreeNode curNode = queue.pollLast();
                depthList.add(curNode.val);
                if (curNode.left != null) {
                    queue.addFirst(curNode.left);
                }

                if (curNode.right != null) {
                    queue.addFirst(curNode.right);
                }
            }
        }
        // 反转
//        Collections.reverse(resList);
        return resList;
    }

    /**
     * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     *
     * @param root 根节点
     * @return 之字形层序遍历
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addFirst(root);

        List<List<Integer>> resList = new ArrayList<>();
        int depth = 0;
        while (!queue.isEmpty()) {

            // 该层正序还是反序
            boolean asc = depth++ % 2 == 0;

            int queueSize = queue.size();

            List<Integer> depthList = new LinkedList<>();
            resList.add(depthList);
            while (queueSize-- > 0) {

                TreeNode curNode = queue.pollLast();
                if (asc) {
                    depthList.add(curNode.val);
                } else {
                    depthList.add(0, curNode.val);
                }
                if (curNode.left != null) {
                    queue.addFirst(curNode.left);
                }

                if (curNode.right != null) {
                    queue.addFirst(curNode.right);
                }
            }
        }

        return resList;
    }


    /**
     * 非递归版本先序遍历（栈）
     *
     * @param treeNode 头节点
     */
    public void preorderTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(treeNode);

        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pollFirst();
            System.out.print(curNode.val + ",");

            // 先压右节点，出栈的时候就是左边先出
            if (curNode.right != null) {
                stack.addFirst(curNode.right);
            }

            if (curNode.left != null) {
                stack.addFirst(curNode.left);
            }
        }
    }

    /**
     * 非递归版本中序遍历
     * 1.有左节点就压入左节点，同时把左节点赋值给cur，直到cur为空时停下
     * 2.cur为空时，栈弹出节点，打印当前节点，cur = 弹出节点的右节点
     * 3.直到栈为空和cur为空停止循环
     *
     * @param treeNode 头节点
     */
    public void inorderTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = treeNode;

        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.addFirst(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pollFirst();
                System.out.print(node.val + ",");
                cur = node.right;
            }

        }
    }

    /**
     * 非递归版本后序遍历(栈)
     *
     * @param treeNode 头节点
     */
    public void postorderTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(treeNode);

        // 辅助节点，记录上一个打印的节点
        TreeNode help = null;
        while (!stack.isEmpty()) {
            TreeNode curNode = stack.peek();

            if (curNode.left != null && curNode.left != help && curNode.right != help) {
                // 当前左节不为空，且未出过栈，压左节点
                stack.addFirst(curNode.left);
            } else if (curNode.right != null && curNode.right != help) {
                // 当前右节点不为空，并且没有出过栈,压右节点
                stack.addFirst(curNode.right);
            } else {
                System.out.print(curNode.val + ",");
                stack.pollFirst();
                help = curNode;
            }

        }
    }


    /**
     * 递归版本遍历
     *
     * @param treeNode 树的头节点
     */
    public void traversalRecursion(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        // 1.先序遍历
//        System.out.print(treeNode.val + ",");

        // 先左节点
        traversalRecursion(treeNode.left);

        // 2.中序遍历
//          System.out.print(treeNode.val + ",");

        // 后右节点
        traversalRecursion(treeNode.right);

        // 3.后序遍历
        System.out.print(treeNode.val + ",");
    }

}
