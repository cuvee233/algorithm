package com.weiyi.alorithm.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 判读树是否完全二叉树
 * 完全二叉树又称满二叉树,层序遍历到第一个孩子节点不全的节点之后所有的节点都为叶子节点
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

}
