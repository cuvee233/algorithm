package com.weiyi.leetcode.tree;

import com.weiyi.alorithm.tree.TreeNode;

/**
 * 二叉树减枝
 * <p>
 * 给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。
 * <p>
 * 节点 node 的子树为 node 本身，以及所有 node 的后代。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/pOCWxh
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2023-01-09
 */
public class PruneTree {

    public TreeNode pruneTree(TreeNode root) {
        if (isZeroVal(root)) {
            return null;
        }
        return root;
    }

    public boolean isZeroVal(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean leftIsZeroVal = isZeroVal(root.left);
        boolean rightIsZeroVal = isZeroVal(root.right);

        if (leftIsZeroVal) {
            root.left = null;
        }
        if (rightIsZeroVal) {
            root.right = null;
        }

        // 左节点可以剪枝，右节点可以剪枝，当前节点==0则整个可以剪枝
        return root.val == 0 && leftIsZeroVal && rightIsZeroVal;
    }

}
