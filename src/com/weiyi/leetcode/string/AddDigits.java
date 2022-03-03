package com.weiyi.leetcode.string;

/**
 * desc: 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 * <p>
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/3/3
 */
public class AddDigits {

    public static void main(String[] args) {
        AddDigits addDigits = new AddDigits();
        System.out.println(addDigits.addDigits(234));
    }

    public int addDigits(int num) {
        if (num < 10) {
            return num;
        }
        int temp = 0;
        while (num != 0) {
            temp += num % 10;
            num /= 10;
        }

        return addDigits(temp);
    }

}
