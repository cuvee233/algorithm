package com.weiyi.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 你和一群强盗准备打劫银行。给你一个下标从 0 开始的整数数组 security ，其中 security[i] 是第 i 天执勤警卫的数量。日子从 0 开始编号。同时给你一个整数 time 。
 * <p>
 * 如果第 i 天满足以下所有条件，我们称它为一个适合打劫银行的日子：
 * <p>
 * 第 i 天前和后都分别至少有 time 天。
 * 第 i 天前连续 time 天警卫数目都是非递增的。
 * 第 i 天后连续 time 天警卫数目都是非递减的。
 * <p>
 * 思路：三次遍历，第一次，拿到每个位置的之前位置到当前位置的递增次数，第二次，拿到当前位置往后的最大递减次数，第三次，判断三个条件是否成立
 * 优化，两遍循环即可
 *
 * @author weiyi
 * @date 2022/3/6
 */
public class GoodDaysToRobBank {

    public static void main(String[] args) {
        GoodDaysToRobBank goodDaysToRobBank = new GoodDaysToRobBank();
        goodDaysToRobBank.goodDaysToRobBank2(new int[]{2,1,2}, 1).forEach(System.out::println);
    }

    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int length = security.length;
        if (length <= time) {
            return Collections.emptyList();
        }

        List<Integer> ans = new ArrayList<>();
        int[] decrArr = new int[length];
        int[] incrArr = new int[length];

        for (int i = 1; i < length; i++) {
            if (security[i - 1] >= security[i]) {
                decrArr[i] = decrArr[i - 1] + 1;
            }
        }

        for (int i = length - 2; i >= 0; i--) {
            if (security[i + 1] >= security[i]) {
                incrArr[i] = incrArr[i + 1] + 1;
            }
        }

        for (int i = time; i < length - time; i++) {
            if (incrArr[i] >= time && decrArr[i] >= time) {
                ans.add(i);
            }
        }

        return ans;
    }

    /**
     * @param security
     * @param time
     * @return
     */
    public List<Integer> goodDaysToRobBank2(int[] security, int time) {
        int length = security.length;
        if (length <= time) {
            return Collections.emptyList();
        }

        List<Integer> ans = new ArrayList<>();
        int[] decrArr = new int[length];
        int[] incrArr = new int[length];

        for (int i = 1; i < length; i++) {
            if (security[i - 1] >= security[i]) {
                decrArr[i] = decrArr[i - 1] + 1;
            }

            if (security[length - i] >= security[length - i - 1]) {
                incrArr[length - i - 1] = incrArr[length - i] + 1;
            }
        }


        for (int i = time; i < length - time; i++) {
            if (incrArr[i] >= time && decrArr[i] >= time) {
                ans.add(i);
            }
        }

        return ans;
    }

}
