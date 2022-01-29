package com.weiyi.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * desc: 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/29
 */
public class Permute {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        permute(nums, lists, new ArrayList<>());
        return lists;
    }

    /**
     * [1,2,3] 1
     * @param nums  初始数组
     * @param lists 结果集合
     * @param list  当前正在排列的集合
     */
    public void permute(int[] nums, List<List<Integer>> lists, List<Integer> list) {
        if (nums.length == list.size()) {
            // base case 全排列生成
            lists.add(list);
            return;
        }

        // 选当前位置

        // 不选当前位置



    }
}
