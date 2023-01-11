package com.weiyi.alorithm.recusion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 打印字符串的全部子序列(子序列只需要保持相对顺序一致，并不要求连续，不是子串)
 * <p>
 * 子串：需要连续   abc -> a b c ab bc ""
 * 子序列：不用连续，但是相对顺序需要一致      abc -> a b c ab ac bc ""
 * 全排列：当前字符所有的排列，不要求连续也不要求相对顺序，abc -> acb abc bac bca cab cba
 *
 * @author weiyi
 * @date 2023-01-11
 */
public class PrintStrAllSubSequence {

    private Set<String> subSequences = new HashSet<>();

    public static void main(String[] args) {
        PrintStrAllSubSequence printStrAllSubSequenceing = new PrintStrAllSubSequence();
        printStrAllSubSequenceing.getAllSubSequence("aab").forEach(System.out::println);
    }

    public List<String> getAllSubSequence(String str) {
        // "abc" -> "a","b","c","ab","ac""abc"
        List<String> SubSequenceList = new ArrayList<>();

        processAllSubSequence2(str, "", 0, SubSequenceList);

        return SubSequenceList;
    }

    /**
     * 当前遍历到n位置，可以选择要n位置或不要n位置
     *
     * @param str         原始字符串
     * @param subSequence 当前拼接的subSequence
     * @param n           当前抉择的字符位置
     * @param res         结果数组
     */
    public void processAllSubSequence(String str, String subSequence, int n, List<String> res) {
        if (n == str.length()) {
            // 终止条件，字符已经遍历完，拼接字符串返回
            res.add(subSequence);
            return;
        }

        // 不选择当前字符
        processAllSubSequence(str, subSequence, n + 1, res);

        // 选择当前字符
        processAllSubSequence(str, subSequence + str.charAt(n), n + 1, res);
    }

    /**
     * 获取字符串的全部非重复子序列
     *
     * @param str 原始字符
     * @return 非重复子序列
     */
    public List<String> getAllSubSequence2(String str) {
        // "abc" -> "a","b","c","ab","ac""abc"
        List<String> SubSequenceList = new ArrayList<>();

        processAllSubSequence2(str, "", 0, SubSequenceList);

        return SubSequenceList;
    }

    /**
     * 当前遍历到n位置，可以选择要n位置或不要n位置
     *
     * @param str         原始字符串
     * @param subSequence 当前拼接的subSequence
     * @param n           当前抉择的字符位置
     * @param res         结果数组
     */
    public void processAllSubSequence2(String str, String subSequence, int n, List<String> res) {
        if (n == str.length()) {
            // 终止条件，字符已经遍历完，拼接字符串返回
            res.add(subSequence);
            return;
        }

        // 不选择当前字符
        processAllSubSequence2(str, subSequence, n + 1, res);

        // 选择当前字符
        String curStr = subSequence + str.charAt(n);
        if (subSequences.contains(curStr)) {
            // 剪枝
            return;
        }
        subSequences.add(curStr);
        processAllSubSequence2(str, curStr, n + 1, res);
    }

}
