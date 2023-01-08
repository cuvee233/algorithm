package com.weiyi.alorithm.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 判读树是否完全二叉树
 * 完全二叉树层序遍历到第一个孩子节点不全的节点之后所有的节点都为叶子节点
 * 要么从第一层到最后一层都是满的，要么最后一层不满，但是是从左到右依次排列的
 * <p>
 * <p>
 * 解题思路：层序遍历，
 * 1.如果一个节点的左节点为空，但是右节点不为空则不为完全二叉树。
 * 2.层序遍历，找到第一个子节点不全的节点，后面出现非叶子节点，也就是有孩子的节点，则不为完全二叉树。
 *
 * @author weiyi
 * @date 2023-01-07
 */
public class CompleteBtree {


    public boolean isCompleteBtree(TreeNode root) {
        if (root == null) {
            return false;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addFirst(root);
        boolean isLeaf = false;

        while (!queue.isEmpty()) {
            TreeNode curNode = queue.pollLast();

            // 有右节点，但无左节点，不符合完全二叉树
            if (curNode.left == null && curNode.right != null) {
                return false;
            }

            // 第一个孩子节点不全的节点之后的节点非叶子节点，不符合完全二叉树
            if (isLeaf && curNode.left != null) {
                return false;
            }

            // 判断是否到了最后一个孩子节点不全的节点
            if (!isLeaf && (curNode.left == null || curNode.right == null)) {
                isLeaf = true;
            }

            // 左右子节点压入队列
            if (curNode.left != null) {
                queue.addFirst(curNode.left);
            }
            if (curNode.right != null) {
                queue.addFirst(curNode.right);
            }
        }

        return true;
    }


    /**
     * 递归方式判断是否完全二叉树
     * <p>
     * 分析情况，一共有四种满足完全二叉树的情形
     * 1.左节点满二叉树，右节点满二叉树，高度一致
     * 2.左节点满二叉树，右节点满二叉树，左节点比右节点最大高度大一
     * 3.左节点非满二叉树的完全二叉树，右节点满二叉树，左节点比右节点最大高度大一
     * 4.左节点满二叉树，右节点完全二叉树，高度一致
     *
     * @param root 头节点
     * @return 是否完全二叉树
     */
    public boolean isCompleteBtree2(TreeNode root) {
        if (root == null) {
            return false;
        }
        return getTreeInfo(root).isComplete;
    }

    public TreeInfo getTreeInfo(TreeNode root) {
        if (root == null) {
            return new TreeInfo(true, true, 0);
        }

        TreeInfo leftInfo = getTreeInfo(root.left);
        TreeInfo rightInfo = getTreeInfo(root.right);

        int maxHigh = Math.max(leftInfo.maxHigh, rightInfo.maxHigh) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.maxHigh == rightInfo.maxHigh;
        boolean isComplete = false;

        if (leftInfo.isFull && rightInfo.isFull && leftInfo.maxHigh == rightInfo.maxHigh) {
            //  左节点是满二叉树，右节点是满二叉树，当前树是满二叉树
            isComplete = true;
        } else if (leftInfo.isFull && rightInfo.isFull && leftInfo.maxHigh == rightInfo.maxHigh + 1) {
            // 左节点满二叉树，右节点满二叉树，左节点比右节点最大高度大一
            isComplete = true;
        } else if (rightInfo.isFull && leftInfo.isFull && leftInfo.maxHigh == rightInfo.maxHigh + 1) {
            // 左节点非满二叉树的完全二叉树，右节点满二叉树，左节点比右节点最大高度大一
            isComplete = true;
        } else if (rightInfo.isFull && leftInfo.isComplete && leftInfo.maxHigh == rightInfo.maxHigh + 1) {
            // 左节点满二叉树，右节点完全二叉树，高度一致
            isComplete = true;
        }

        return new TreeInfo(isFull, isFull, maxHigh);
    }


    static class TreeInfo {
        /**
         * 以当前节点为树是否为满二叉树
         */
        protected boolean isFull;

        /**
         * 以当前节点为树是否为完全二叉树
         */
        protected boolean isComplete;

        /**
         * 最大高度
         */
        protected int maxHigh;

        public TreeInfo(boolean isFull, boolean isComplete, int maxHigh) {
            this.isFull = isFull;
            this.maxHigh = maxHigh;
            this.isComplete = isComplete;
        }
    }

}
