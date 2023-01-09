package com.weiyi.leetcode.tree;

import com.weiyi.alorithm.tree.TreeNode;

/**
 * 给你一棵二叉树的根节点 root ，找出并返回满足要求的节点数，要求节点的值等于其 子树 中值的 平均值 。
 * <p>
 * 注意：
 * <p>
 * n 个元素的平均值可以由 n 个元素 求和 然后再除以 n ，并 向下舍入 到最近的整数。
 * root 的 子树 由 root 和它的所有后代组成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-nodes-equal-to-average-of-subtree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 递归套路实现，每个节点返回父节点，以当前节点为root的元素个数，与元素总值，父节点汇总左子节点右子节点数据来计算
 *
 * @author weiyi
 * @date 2023-01-09
 */
public class AverageOfSubtree {

    public int averageOfSubtree(TreeNode root) {
        return getTreeInfo(root).count;
    }

    public TreeInfo getTreeInfo(TreeNode root) {
        if (root == null) {
            return new TreeInfo(0, 0, 0);
        }

        // left
        TreeInfo leftInfo = getTreeInfo(root.left);

        // right
        TreeInfo rightInfo = getTreeInfo(root.right);

        int nodeCount = leftInfo.nodeCount + rightInfo.nodeCount + 1;
        int totalVal = leftInfo.totalVal + rightInfo.totalVal + root.val;

        int count = leftInfo.count + rightInfo.count;
        if (totalVal / nodeCount == root.val) {
            count++;
        }
        return new TreeInfo(nodeCount, totalVal, count);
    }

    static class TreeInfo {
        /**
         * 当前节点为头的树节点总数
         */
        protected int nodeCount;

        /**
         * 当前节点为头的树的总值
         */
        protected int totalVal;

        /**
         * 符合要求的子树数量
         */
        protected int count;

        protected TreeInfo(int nodeCount, int totalVal, int count) {
            this.nodeCount = nodeCount;
            this.totalVal = totalVal;
            this.count = count;
        }
    }

}
