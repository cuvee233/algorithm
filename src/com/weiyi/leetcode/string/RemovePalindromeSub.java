package com.weiyi.leetcode.string;

/**
 * 给你一个字符串 s，它仅由字母 'a' 和 'b' 组成。每一次删除操作都可以从 s 中删除一个回文 子序列。
 * <p>
 * 返回删除给定字符串中所有字符（字符串为空）的最小删除次数。
 * <p>
 * 这题的解题思路是“子序列”非“字串”然后，只有两种字母，a和b，所以最多也只有两种情况
 *
 * @author weiyi
 * @date 2022/1/22
 */
public class RemovePalindromeSub {

    public static void main(String[] args) {
        tribonacci(37);
    }

    public int removePalindromeSub(String s) {
        int length = s.length();
        int n = length >> 1;

        // 只有两种情况，一种是s本身就是回文子序列，那么结果为1，另一种情况则是s非回文子序列，结果为2
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != s.charAt(length - 1 - i)) {
                return 2;
            }
        }

        return 1;
    }

    public static int tribonacci(int n) {
        if(n <= 1){
            return n;
        }
        int ans = 0;
        for(int i = 1; i < n; i++){
            ans+=tribonacci(i);
        }
        return ans;
    }
}
