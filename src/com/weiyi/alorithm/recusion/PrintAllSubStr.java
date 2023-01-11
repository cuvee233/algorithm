package com.weiyi.alorithm.recusion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 打印字符串全部子串，连续的才是子串
 *
 * @author weiyi
 * @date 2023-01-11
 */

public class PrintAllSubStr {

    private static HashSet<String> subStringSet = new HashSet<>();

    public static void main(String[] args) {
        getAllSubStr("abc").forEach(System.out::println);

    }

    public static List<String> getAllSubStr(String str) {

        List<String> subStrList = new ArrayList<>();

        getAllSubStr(str, 0, "", subStrList);

        return subStrList;
    }

    public static void getAllSubStr(String str, int n, String subStr, List<String> subStrList) {
        // abc abc ab a bc  b c
        if (subStringSet.contains(subStr)) {
            return;
        }
        subStringSet.add(subStr);

        subStrList.add(subStr);
        // 是否要当前位置字符
        if (n == str.length()) {
            return;
        }

        // 要么拼在一起，要么自己做头

        // 自己做头
        getAllSubStr(str, n + 1, String.valueOf(str.charAt(n)), subStrList);

        // 拼在一起
        getAllSubStr(str, n + 1, subStr + str.charAt(n), subStrList);
    }


}
