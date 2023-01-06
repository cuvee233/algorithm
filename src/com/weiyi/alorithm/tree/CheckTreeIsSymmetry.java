package com.weiyi.alorithm.tree;

/**
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 * @author weiyi
 * @date 2023-01-06
 */
public class CheckTreeIsSymmetry {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }

        return isEquals(root.left, root.right);
    }

    public boolean isEquals(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.val != root2.val) {
            return false;
        }


        // 左节点
        boolean leftIsEquals = isEquals(root1.left, root2.right);

        // 右节点
        boolean rightIsEquals = isEquals(root1.right, root2.left);

        return leftIsEquals && rightIsEquals;
    }

}
