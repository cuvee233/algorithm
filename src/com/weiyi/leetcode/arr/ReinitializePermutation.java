package com.weiyi.leetcode.arr;

/**
 * 给你一个偶数 n​​​​​​ ，已知存在一个长度为 n 的排列 perm ，其中 perm[i] == i​（下标 从 0 开始 计数）。
 * <p>
 * 一步操作中，你将创建一个新数组 arr ，对于每个 i ：
 * <p>
 * 如果 i % 2 == 0 ，那么 arr[i] = perm[i / 2]
 * 如果 i % 2 == 1 ，那么 arr[i] = perm[n / 2 + (i - 1) / 2]
 * 然后将 arr​​ 赋值​​给 perm 。
 * <p>
 * 要想使 perm 回到排列初始值，至少需要执行多少步操作？返回最小的 非零 操作步数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-number-of-operations-to-reinitialize-a-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 1.笨办法，模拟实现，n最大为1000，生成对应长度的数组来模拟实现
 *
 * @author weiyi
 * @date 2023-01-09
 */
public class ReinitializePermutation {

    public static void main(String[] args) {
        ReinitializePermutation reinitializePermutation = new ReinitializePermutation();
        System.out.println(reinitializePermutation.reinitializePermutation(2000));
    }

    public int reinitializePermutation(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        // 辅助数组
        int[] help = new int[n];

        int ans = 0;
        while (true) {
            ans++;
            for (int i = 0; i < arr.length; i++) {
                if (i % 2 == 0) {
                    help[i] = arr[i / 2];
                } else {
                    help[i] = arr[n / 2 + (i - 1) / 2];
                }
            }

            boolean isEqual = true;
            for (int i = 0; i < help.length; i++) {
                if (help[i] != i) {
                    isEqual = false;
                    break;
                }
            }
            if (isEqual) {
                break;
            }
            int[] temp = arr;
            arr = help;
            help = temp;
        }
        return ans;
    }

}
