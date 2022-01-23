package com.weiyi.leetcode.nQueens;

/**
 * N皇后问题
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * <p>
 * 1.数组模拟法
 * 定义一个长度为n的数组，索引i代表i+1行，num[i]代表当前行选择的第几列，依次递归下去，
 * 如果后续层没有能正确放置的位置，则推倒重来，回上一层找另外合适的位置
 * 2.位运算模拟
 *
 * @author weiyi
 * @date 2022/1/21
 */
public class NQueens {

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        System.out.println(nQueens.totalNQueens(5));
    }

    public int totalNQueens(int n) {
        return totalNQueens(new int[n], 0, n);
    }

    /**
     * @param nums      行数组，nums[i]是当前列选择的值
     * @param curRow 当前遍历到了第几行
     * @param n         n皇后的规模
     * @return 总共多少答案
     */
    public int totalNQueens(int[] nums, int curRow, int n) {
        if (curRow == n) {
            // 当curColumn等于n的时候，代表所有行都能找到争正确的位置放置皇后，也就是找到了一种正确答案（base case）
            return 1;
        }

        int res = 0;
        // 一列一列的尝试
        for (int i = 0; i < n; i++) {
            if (isValidate(nums, curRow, i)) {
                // 当前位置可以放置，进入下一行
                nums[curRow] = i;
                res += totalNQueens(nums, curRow + 1, n);
            }
        }

        return res;
    }

    /**
     * 当前数组nums的行curColumn是否能放i列的皇后，
     * 两种情况，
     * 一种是i不能等于nums里面的元素（共列），
     * 一种是行之间的绝对值不能等于列之间的绝对值（共对角线）
     *
     * @param nums      n皇后棋牌行
     * @param curRow    当前行
     * @param i         当前列
     * @return i位置是否可以放置
     */
    private boolean isValidate(int[] nums, int curRow, int i) {
        for (int j = 0; j < curRow; j++) {
            if ((nums[j] == i) || Math.abs(nums[j] - i) == Math.abs(curRow - j)) {
                return false;
            }
        }
        return true;
    }


}
