package com.weiyi.leetcode;

/**
 * desc: 给你一个二维整数数组ranges和两个整数left和right。每个ranges[i] = [starti, endi]表示一个从start[i]到end[i]的闭区间。
 * <p>
 * 如果闭区间[left, right]内每个整数都被ranges中至少一个区间覆盖，那么请你返回true，否则返回false。
 * <p>
 * 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 start[i] <= x <= end[i]，那么我们称整数x被覆盖了。
 * <p>
 * 输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * 输出：true
 * 解释：2 到 5 的每个整数都被覆盖了：
 * - 2 被第一个区间覆盖。
 * - 3 和 4 被第二个区间覆盖。
 * - 5 被第三个区间覆盖。
 *
 * @author yuanwei
 * @version 1.0
 * @date 2021/7/23
 */
public class CheckRangeValue {
}
