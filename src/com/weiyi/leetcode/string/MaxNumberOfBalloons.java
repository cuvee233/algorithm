package com.weiyi.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 * <p>
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 *
 * @author weiyi
 * @date 2022/2/13
 */
public class MaxNumberOfBalloons {

    public int maxNumberOfBalloons(String text) {
        int length = text.length();
        if (length < 6) {
            return 0;
        }

        Map<Character, Integer> countMap = new HashMap<>();

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if (c == 'b' || c == 'a' || c == 'n') {
                countMap.put(c, countMap.getOrDefault(c, 0) + 2);
            } else if (c == 'l' || c == 'o') {
                // l和o需要两个
                countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            }
        }

        // 字母不够拼接"balloon"
        if (countMap.size() < 5) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for (Integer count : countMap.values()) {
            ans = Math.min(ans, count / 2);
        }

        return ans;
    }

}
