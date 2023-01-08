package com.weiyi.alorithm.tree;

/**
 * 是否二叉搜索树
 * <p>
 * 二叉搜索树：左树所有节点的元素都小于等于根节点，右树都大于根节点
 *
 * @author weiyi
 * @date 2023-01-07
 */
public class IsBST {

    public boolean isBst(TreeNode root) {
        if (root == null) {
            return false;
        }

        return checkIsBst(root).isBst;
    }

    public TreeInfo checkIsBst(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 左树是否二叉搜索树
        TreeInfo leftInfo = checkIsBst(root.left);

        // 右树是否二叉搜索树
        TreeInfo rightInfo = checkIsBst(root.left);


        // 当前根节点是否符合完全二叉树
        if (leftInfo != null && (!leftInfo.isBst || leftInfo.max > root.val)) {
            return new TreeInfo(false);
        }
        if (rightInfo != null && (!rightInfo.isBst || rightInfo.min <= root.val)) {
            return new TreeInfo(false);
        }

        int max = root.val;
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
        } else if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }

        return new TreeInfo(max, true);
    }

    static class TreeInfo {

        /**
         * 当前子树的最大值
         */
        public int max;

        /**
         * 当前子树的最小值
         */
        public int min;
        /**
         * 当前子树是否是二叉搜索树
         */
        public boolean isBst;

        public TreeInfo() {

        }

        public TreeInfo(boolean isBst) {
            this.isBst = isBst;
        }

        public TreeInfo(int max, boolean isBst) {
            this.max = max;
            this.isBst = isBst;
        }
    }
}
