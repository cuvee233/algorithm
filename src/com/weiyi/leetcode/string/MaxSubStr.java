package com.weiyi.leetcode.string;

import java.util.HashMap;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * i位置字符串，两种情况
 *
 * @author weiyi
 * @date 2022/1/21
 */
public class MaxSubStr {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("bbbbb"));
    }

    /**
     * 暴力解法，效率低，没办法ac
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;

        int maxSubLength = 0;
        // 当前滑动的第一个数字加上当前字符为key,value随意
        HashMap<String, Integer> strCountMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            // 默认选中当前字符串，max为1
            int curMax = 1;
            strCountMap.put(i + String.valueOf(chars[i]), 1);
            for (int j = i + 1; j < length; j++) {
                String key = i + String.valueOf(chars[j]);
                // 当前字符串没被使用过，max加一，放进map,继续下一个字符串
                if (!strCountMap.containsKey(key)) {
                    strCountMap.put(key, 1);
                    curMax++;
                    continue;
                }
                break;
            }
            if (curMax > maxSubLength) {
                maxSubLength = curMax;
            }
        }
        return maxSubLength;
    }

    /**
     * 优化方案，滑动窗口，没必要每次都重新开始遍历
     * 以 (a)bcabcbb 开始的最长字符串为 (abc)abcbb；
     * 以 a(b)cabcbb 开始的最长字符串为 a(bca)bcbb；
     * 以 ab(c)abcbb 开始的最长字符串为 ab(cab)cbb；
     * 以 abc(a)bcbb 开始的最长字符串为 abc(abc)bb；
     * 以 abca(b)cbb 开始的最长字符串为 abca(bc)bb；
     * <p>
     * 依次递增地枚举子串的起始位置，那么子串的结束位置也是递增的
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();

        int maxSubLength = 0;
        // 记录字符是否出现，会滑动删除
        HashMap<Character, Object> charMap = new HashMap<>();
        // 记录上一次遍历到的位置
        int lastIndex = -1;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                // 移除上一次的字符
                charMap.remove(s.charAt(i - 1));
            }

            // 结束条件，遍历到最后一个字符串或者下一个字符已经出现
            while (lastIndex + 1 < length && !charMap.containsKey(s.charAt(lastIndex + 1))) {
                charMap.put(s.charAt(lastIndex + 1), null);
                lastIndex++;
            }

            // lastIndex减去第一个字符串的索引再加一等于当前不重复字串的长度
            maxSubLength = Math.max(maxSubLength, lastIndex - i + 1);

        }

        return maxSubLength;
    }

}
