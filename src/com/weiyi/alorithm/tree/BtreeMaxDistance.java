package com.weiyi.alorithm.tree;

/**
 * 二叉树的最大距离
 *
 * @author weiyi
 * @date 2023-01-08
 */
public class BtreeMaxDistance {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode10 = new TreeNode(10);
        TreeNode treeNode11 = new TreeNode(11);
        TreeNode treeNode12 = new TreeNode(12);
        TreeNode treeNode13 = new TreeNode(13);
        TreeNode treeNode14 = new TreeNode(14);
        TreeNode treeNode15 = new TreeNode(15);

        treeNode1.left = treeNode2;

        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;

        treeNode3.left = treeNode5;

        treeNode4.left = treeNode6;

        treeNode5.left = treeNode7;
        treeNode6.right = treeNode8;

//        treeNode6.left = treeNode12;
//        treeNode6.right = treeNode13;
//
//        treeNode7.left = treeNode14;
//        treeNode7.right = treeNode15;

        BtreeMaxDistance btreeMaxDistance = new BtreeMaxDistance();
        System.out.println(btreeMaxDistance.getMaxDistance(treeNode1));
    }

    public int getMaxDistance(TreeNode root) {
        // 递归
        if (root == null) {
            return 0;
        }
        return getMaxDistanceInfo(root).maxDistance;
    }

    /**
     * 求当前头节点的最大深度
     *
     * @param root 头节点
     * @return 当前树的最大深度
     */
    public TreeInfo getMaxDistanceInfo(TreeNode root) {
        if (root == null) {
            return new TreeInfo(0, 0);
        }

        // 左子节点的信息
        TreeInfo leftInfo = getMaxDistanceInfo(root.left);

        // 右子节点的信息
        TreeInfo rightInfo = getMaxDistanceInfo(root.right);

        // 经过当前节点的最大距离
        int curMaxDistance = leftInfo.maxDepth + rightInfo.maxDepth + 1;

        // 孩子节点的最大距离和当前节点的最大距离找出最大距离
        curMaxDistance = Math.max(curMaxDistance, Math.max(leftInfo.maxDistance, rightInfo.maxDistance));

        int maxDepth = Math.max(leftInfo.maxDepth, rightInfo.maxDepth) + 1;

        return new TreeInfo(maxDepth, curMaxDistance);
    }

    static class TreeInfo {
        /**
         * 以当前节点为头的树的最大深度
         */
        protected int maxDepth;

        /**
         * 以当前节点为头的最大距离
         */
        protected int maxDistance;

        public TreeInfo(int maxDepth, int maxDistance) {
            this.maxDepth = maxDepth;
            this.maxDistance = maxDistance;
        }
    }

}
