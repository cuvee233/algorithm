package com.weiyi.alorithm.xor;

/**
 * 异或特性
 * 1。任何数n异或0 = n
 * 2.n^n=n
 * 3.a^b=b^a
 *
 * @author weiyi
 * @date 2022-12-28
 */
public class EvenTimesOddTimes {

    public static void main(String[] args) {
//        int[] the2OddNum = findThe2OddNum(new int[]{1, 1, 1, 2, 3, 3, 1, 6});
//        System.out.println(the2OddNum[0] + "..." + the2OddNum[1]);

        System.out.println(findK(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4}, 2, 3));
    }


    /**
     * 查找数组中唯一出现奇数次的数
     * <p>
     * 偶数次的数异或=0，剩下的只剩下奇数的数
     */
    public static int findTheOddNum(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }

        return res;
    }


    /**
     * 查找数组中唯二出现奇数次的数
     * 1.将所有数字异或，最终结果一定是等于，x^y
     * 2.找到x和y二进制数字不相同的最低位( (n & (-n)) 或 (n & (~n+1)) )
     * 3.通过最低位的1将数组元素分为两批
     * 4.两批元素分别异或找到不同的两个数
     */
    public static int[] findThe2OddNum(int[] nums) {
        // 计算x^y的值
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        // 找到最低位的1
        int lowBit = xor & (~xor + 1);

        // 找到其中一个数
        int other = 0;
        for (int num : nums) {
            if ((lowBit & num) == 0) {
                other ^= num;
            }
        }

        return new int[]{other, xor ^ other};
    }

    /**
     * 给定一个数组nums，有一个元素出现了k次，其它元素都出现了m次，找出这个元素，要求额外空间复杂度为O(n)
     */

    public static int findK(int[] nums, int k, int m) {
        // 数组当成一个二进制数组，每一位存储当前位的总数
        int[] bitArr = new int[32];

        // 计算位总数
        for (int num : nums) {
            for (int i = 0; i < bitArr.length; i++) {
                bitArr[i] += (num >> i & 1);
            }
        }

        int res = 0;

        // 当任意一位%m不等于0时，代表k元素此位为1
        for (int i = 0; i < bitArr.length; i++) {

            if (bitArr[i] % m != 0) {
                // k出现了,还原k值
                res |= 1 << i;
            }

        }
        return res;
    }


    /**
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     */
    public int singleNumber(int[] nums) {
        // 数组当成一个二进制数组，每一位存储当前位的总数
        int[] bitArr = new int[32];

        // 计算总位数
        for (int num : nums) {
            for (int i = 0; i < bitArr.length; i++) {
                bitArr[i] += (num >> i & 1);
            }
        }

        int res = 0;
        for (int i = 0; i < bitArr.length; i++) {
            if (bitArr[i] % 3 != 0) {
                res |= 1 << i;
            }
        }

        return res;
    }
}
