package com.weiyi.leetcode.dp;

import com.weiyi.alorithm.tree.TreeNode;

/**
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * <p>
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * <p>
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2023-02-01
 */
public class Rob3 {

    public int rob(TreeNode root) {
        int[] values = process(root);
        return Math.max(values[0], values[1]);
    }

    /**
     * 递归返回选当前节点和不选当前节点的最大值
     *
     * @param root 当前节点
     * @return arr[0] 选前节点最大值 arr[1] 不选当前节点最大值
     */
    public int[] process(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        // 左子节点
        int[] leftValues = process(root.left);

        // 右子节点
        int[] rightValues = process(root.right);

        // 选当前节点则选择不选直属子节点的最大值加上当前val
        int pick = leftValues[1] + rightValues[1] + root.val;

        // 不选当前节点则选择直属子节点最大值
        int notPick = leftValues[0] + rightValues[0];

        // 选择当前节点的最大值应该包含不选当前节点这种情况，所以需要在两种情况里面取最大值
        pick = Math.max(pick, notPick);

        return new int[]{pick, notPick};
    }

}
