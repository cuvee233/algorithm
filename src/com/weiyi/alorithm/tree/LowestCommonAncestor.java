package com.weiyi.alorithm.tree;

/**
 * 找到两个节点的最近公共祖先
 * <p>
 * 1.可以用map存储所有节点的父节点，然后找到a的所有父节点，放入set，递归b的父节点，判断是否存在set
 * 2.递归调用
 *
 * @author weiyi
 * @date 2023-01-08
 */
public class LowestCommonAncestor {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        return getTreeInfo(root, p, q).lowestAncestor;
    }

    public TreeInfo getTreeInfo(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new TreeInfo(false, false, null);
        }

        TreeInfo leftInfo = getTreeInfo(root.left, p, q);
        TreeInfo rightInfo = getTreeInfo(root.right, p, q);

        boolean findP = root == p || leftInfo.findP || rightInfo.findP;
        boolean findQ = root == q || leftInfo.findQ || rightInfo.findQ;

        TreeNode lowestAncestor = leftInfo.lowestAncestor != null ? leftInfo.lowestAncestor : rightInfo.lowestAncestor;
        if (lowestAncestor == null && findP && findQ) {
            lowestAncestor = root;
        }

        return new TreeInfo(findP, findQ, lowestAncestor);
    }

    static class TreeInfo {
        protected boolean findP;
        protected boolean findQ;
        protected TreeNode lowestAncestor;

        protected TreeInfo(boolean findP, boolean findQ, TreeNode lowestAncestor) {
            this.findP = findP;
            this.findQ = findQ;
            this.lowestAncestor = lowestAncestor;
        }
    }

}
