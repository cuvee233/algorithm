package com.weiyi.leetcode.tree;

import com.weiyi.alorithm.tree.TreeNode;

/**
 * 有两位极客玩家参与了一场「二叉树着色」的游戏。游戏中，给出二叉树的根节点 root，树上总共有 n 个节点，且 n 为奇数，其中每个节点上的值从 1 到 n 各不相同。
 * <p>
 * 最开始时：
 * <p>
 * 「一号」玩家从 [1, n] 中取一个值 x（1 <= x <= n）；
 * 「二号」玩家也从 [1, n] 中取一个值 y（1 <= y <= n）且 y != x。
 * 「一号」玩家给值为 x 的节点染上红色，而「二号」玩家给值为 y 的节点染上蓝色。
 * <p>
 * 之后两位玩家轮流进行操作，「一号」玩家先手。每一回合，玩家选择一个被他染过色的节点，将所选节点一个 未着色 的邻节点（即左右子节点、或父节点）进行染色（「一号」玩家染红色，「二号」玩家染蓝色）。
 * <p>
 * 如果（且仅在此种情况下）当前玩家无法找到这样的节点来染色时，其回合就会被跳过。
 * <p>
 * 若两个玩家都没有可以染色的节点时，游戏结束。着色节点最多的那位玩家获得胜利 ✌️。
 * <p>
 * 现在，假设你是「二号」玩家，根据所给出的输入，假如存在一个 y 值可以确保你赢得这场游戏，则返回 true ；若无法获胜，就请返回 false 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-tree-coloring-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2023-02-03
 */
public class BtreeGameWinningMove {

    public static void main(String[] args) {
        System.out.println(5 / 2);
    }

    /**
     * 解题思路：找到value为x的节点，判断其父节点、左子节点、右子节点的子树节点数是否超过整个树的一半，如果超过则代表二号玩家可以赢
     * 扩展：如果一号玩家想要必胜，则可以直接选择奇数个节点的树的重心，重心可以通过dfs计算，求以x为头的节点左树加右树的数量，然后用总数量
     *
     * @param root 根节点
     * @param n    节点总数
     * @param x    一号玩家节点
     * @return 二号玩家是否有必胜策略
     */
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        // 找到x节点
        TreeNode xNode = findX(root, x);

        // 左子节点个数
        int leftCount = countNode(xNode.left);
        if (leftCount > (n / 2)) {
            return true;
        }

        // 右子节点个数
        int rightCount = countNode(xNode.right);
        if (rightCount > (n / 2)) {
            return true;
        }

        // 总数 减去 （x左子树数量 + x右子数数量 + 1）为父节点数量
        return n - leftCount - rightCount - 1 > (n / 2);
    }

    public int countNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNode(root.left) + countNode(root.right) + 1;
    }

    public TreeNode findX(TreeNode root, int x) {
        if (root == null) {
            return null;
        }
        if (root.val == x) {
            return root;
        }

        TreeNode findX = findX(root.left, x);
        return findX != null ? findX : findX(root.right, x);
    }

}
