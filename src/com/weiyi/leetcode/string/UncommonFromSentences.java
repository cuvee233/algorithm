package com.weiyi.leetcode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * desc: 给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/30
 */
public class UncommonFromSentences {

    public String[] uncommonFromSentences(String s1, String s2) {
        // 一个map
        Map<String, Integer> countMap = new HashMap<String, Integer>();

        String[] s1Arr = s1.split(" ");
        String[] s2Arr = s2.split(" ");

        for (String s : s1Arr) {
            Integer count = countMap.getOrDefault(s, 0);
            countMap.put(s, count + 1);
        }

        for (String s : s2Arr) {
            Integer count = countMap.getOrDefault(s, 0);
            countMap.put(s, count + 1);
        }

        return countMap.entrySet().stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey).toArray(String[]::new);
    }


    public String[] uncommonFromSentences2(String s1, String s2) {
        // 两个map
        Map<String, Integer> countMap = new HashMap<String, Integer>();

        String[] s1Arr = s1.split(" ");
        String[] s2Arr = s2.split(" ");

        for (String s : s1Arr) {
            Integer count = countMap.getOrDefault(s, 0);
            countMap.put(s, count + 1);
        }

        for (String s : s2Arr) {
            Integer count = countMap.getOrDefault(s, 0);
            countMap.put(s, count + 1);
        }

        return countMap.entrySet().stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey).toArray(String[]::new);
    }
}
