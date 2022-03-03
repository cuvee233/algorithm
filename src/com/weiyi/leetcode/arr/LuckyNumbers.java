package com.weiyi.leetcode.arr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * desc: 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 * <p>
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 * <p>
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 * <p>
 * matrix = [[7,8,5],[1,9,2]]
 * <p>
 * [[3,6],[7,1],[5,2],[4,8]]
 * ans = 7
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/2/15
 */
public class LuckyNumbers {

    public static void main(String[] args) {
        luckyNumbers(new int[][]{{3, 7, 8}, {9, 11, 13}, {15, 16, 17}}).forEach(System.out::println);
//        luckyNumbers(new int[][]{{3, 6}, {7, 1}, {5, 2}, {4, 8}}).forEach(System.out::println);
    }

    public static List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();

        // 每列的最大值
        int[] columnMax = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                columnMax[j] = Math.max(columnMax[j], matrix[i][j]);
            }
        }

        // 每行的最小值
        int[] rowMin = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rowMin[i] = matrix[i][0];
            for (int j = 1; j < matrix[i].length; j++) {
                rowMin[i] = Math.min(rowMin[i], matrix[i][j]);
            }
        }

        // 找出两个数组的交集
        for (int column : columnMax) {
            for (int row : rowMin) {
                if (column == row) {
                    ans.add(row);
                }
            }
        }
        return ans;
    }
}
