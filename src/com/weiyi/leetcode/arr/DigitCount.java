package com.weiyi.leetcode.arr;

/**
 * 给你一个下标从 0 开始长度为 n 的字符串 num ，它只包含数字。
 * <p>
 * 如果对于 每个 0 <= i < n 的下标 i ，都满足数位 i 在 num 中出现了 num[i]次，那么请你返回 true ，否则返回 false 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-if-number-has-equal-digit-count-and-digit-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 1.用容器的方式，第一遍遍历数组的时候将数值出现的频率加到map里面，因为题目表面数组长度最大为10，所以此处用数组会更好,
 * 记录出现次数之后，将数组和原始字符串遍历对比，如果一致返回true，否则false
 *
 * @author weiyi
 * @date 2023-01-11
 */
public class DigitCount {

    public static void main(String[] args) {
        DigitCount digitCount = new DigitCount();
        System.out.println(digitCount.digitCount("3000"));
    }

    public boolean digitCount(String num) {
        int[] countArr = new int[num.length()];

        for (int i = 0; i < num.length(); i++) {
            int count = num.charAt(i) - '0';
            if (count < num.length()) {
                countArr[count]++;
            }
        }


        for (int i = 0; i < num.length(); i++) {
            int count = num.charAt(i) - '0';
            if (count != countArr[i]) {
                return false;
            }
        }
        return true;
    }

}
