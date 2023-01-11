package com.weiyi.alorithm.recusion;

import com.weiyi.sort.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串全排列 abc -> acb abc bac bca cab cba
 *
 * @author weiyi
 * @date 2023-01-11
 */
public class PrintFullStr {

    public static void main(String[] args) {
        PrintFullStr printFullStr = new PrintFullStr();
        printFullStr.getFullStrList("abb").forEach(System.out::println);
        System.out.println("========================");
        printFullStr.getFullStrList2("abb").forEach(System.out::println);
    }

    public List<String> getFullStrList(String str) {
        List<String> fullStrList = new ArrayList<String>();

        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            chars.add(str.charAt(i));
        }

        processFullStr(chars, "", fullStrList);

        return fullStrList;
    }

    public List<String> getFullStrList2(String str) {
        List<String> fullStrList = new ArrayList<String>();

        char[] chars = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            chars[i] = str.charAt(i);
        }

        processFullStr2(chars, 0, fullStrList);

        return fullStrList;
    }

    public void processFullStr(List<Character> chars, String path, List<String> fullStrList) {
        if (chars.isEmpty()) {
            fullStrList.add(path);
            return;
        }

        for (int i = 0; i < chars.size(); i++) {
            // 选择当前字符，后续递归选择
            Character cur = chars.remove(i);
            processFullStr(chars, path + cur, fullStrList);

            // 恢复现场
            chars.add(i, cur);
        }
    }

    /**
     * 优化版本
     *
     * @param chars       所有字符数组
     * @param n           当前处理的字符位置索引
     * @param fullStrList 结果数组
     */
    public void processFullStr2(char[] chars, int n, List<String> fullStrList) {
        if (n == chars.length) {
            // baseCase 将交换好的chars数组转成字符串
            fullStrList.add(String.valueOf(chars));
            return;
        }

        // 去重数组
        boolean[] charArr = new boolean[256];

        for (int i = n; i < chars.length; i++) {

            if (!charArr[chars[i]]) {
                charArr[chars[i]] = true;
                System.out.println(i + " " + chars[i]);
                // 选择当前字符，后续递归选择
                ArrayUtils.swap(chars, i, n);

                processFullStr2(chars, n + 1, fullStrList);

                // 恢复现场
                ArrayUtils.swap(chars, i, n);

            }
        }
    }


}
