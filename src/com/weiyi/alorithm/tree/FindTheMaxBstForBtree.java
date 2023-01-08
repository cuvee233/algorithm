package com.weiyi.alorithm.tree;

/**
 * 给定一个二叉树，返回此二叉树中，以x节点为头的最大搜索二叉树的节点个数
 *
 * @author weiyi
 * @date 2023-01-08
 */
public class FindTheMaxBstForBtree {

    public int getMaxMaxBstForBtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getTreeInfo(root).maxSubBstSize;

    }


    public TreeInfo getTreeInfo(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeInfo leftInfo = getTreeInfo(root.left);
        TreeInfo rightInfo = getTreeInfo(root.right);

        int min = root.val;
        int max = root.val;
        int allNodeSize = 0;

        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            allNodeSize += leftInfo.allNodeSize;
        }

        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            allNodeSize += rightInfo.allNodeSize;
        }

        boolean leftIsNull = leftInfo == null;
        boolean rightIsNull = rightInfo == null;

        // 计算二叉搜索树的最大个数
        int maxSubBstSize = 0;

        boolean leftIsBst = leftIsNull || leftInfo.maxSubBstSize == leftInfo.allNodeSize;
        boolean rightIsBst = rightIsNull || rightInfo.maxSubBstSize == rightInfo.allNodeSize;

        // 左节点和右节点都是bst的时候，判断当前节点是否符合bst
        if (leftIsBst && rightIsBst) {
            boolean curIsBst = (leftIsNull || leftInfo.max < root.val) && (rightIsNull || rightInfo.min > root.val);
            if (curIsBst) {
                maxSubBstSize = (leftIsNull ? 0 : leftInfo.allNodeSize) + (rightIsNull ? 0 : rightInfo.allNodeSize) + 1;
            }
        } else {
            maxSubBstSize = Math.max(leftIsNull ? 0 : leftInfo.maxSubBstSize, rightIsNull ? 0 : rightInfo.maxSubBstSize);
        }

        return new TreeInfo(min, max, maxSubBstSize, allNodeSize);
    }

    static class TreeInfo {
        /**
         * 当前节点以及其子树里面最大搜索二叉树的节点个数
         */
        protected int maxSubBstSize;

        /**
         * 当前节点的所有子节点个数，当allNodeSize = maxSubBstSize 代表当前节点是搜索二叉树
         */
        protected int allNodeSize;

        /**
         * 当前树的最大值与最小值，用于父节点判断是否搜索二叉树
         */
        protected int min;
        protected int max;

        public TreeInfo(int min, int max, int maxSubBstSize, int allNodeSize) {
            this.min = min;
            this.max = max;
            this.maxSubBstSize = maxSubBstSize;
            this.allNodeSize = allNodeSize;
        }
    }

}
