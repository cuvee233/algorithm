package com.weiyi.leetcode.nQueens;

import java.util.ArrayList;
import java.util.List;

/**
 * N皇后问题第二种解法
 * 位运算
 *
 * @author weiyi
 * @date 2022/1/21
 */
public class NQueens2 {

    public static void main(String[] args) {
        NQueens2 nQueens2 = new NQueens2();
        List<List<String>> lists = nQueens2.solveNQueens(5);
        lists.forEach(System.out::println);
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> resList = new ArrayList<>();
        totalNQueens(new int[n], 0, n, resList);
        return resList;
    }

    /**
     * @param nums   行数组，nums[i]是当前列选择的值
     * @param curRow 当前遍历到了第几行
     * @param n      n皇后的规模
     * @return 总共多少答案
     */
    public void totalNQueens(int[] nums, int curRow, int n, List<List<String>> resList) {
        if (curRow == n) {
            // 当curColumn等于n的时候，代表所有行都能找到争正确的位置放置皇后，也就是找到了一种正确答案（base case）

            // 封装答案
            fillResList(nums, resList);
        }

        // 一列一列的尝试
        for (int i = 0; i < n; i++) {
            if (isValidate(nums, curRow, i)) {
                // 当前位置可以放置，进入下一行
                nums[curRow] = i;
                totalNQueens(nums, curRow + 1, n, resList);
            }
        }
    }

    private void fillResList(int[] nums, List<List<String>> resList) {
        List<String> total = new ArrayList<>();
        resList.add(total);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            StringBuilder sb = new StringBuilder();
            int num = nums[i];
            for (int j = 0; j < length; j++) {
                if (j == num) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            total.add(sb.toString());
        }
    }

    /**
     * 当前数组nums的行curColumn是否能放i列的皇后，
     * 两种情况，
     * 一种是i不能等于nums里面的元素（共列），
     * 一种是行之间的绝对值不能等于列之间的绝对值（共对角线）
     *
     * @param nums   n皇后棋牌行
     * @param curRow 当前行
     * @param i      当前列
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
