package com.weiyi.leetcode.arr;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你三个整数数组 nums1、nums2 和 nums3 ，请你构造并返回一个 元素各不相同的 数组，且由 至少 在 两个 数组中出现的所有值组成。数组中的元素可以按 任意 顺序排列。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/two-out-of-three
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2022-12-29
 */
public class TwoOutOfThree {


    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        // 第一个数组置为2，第二个数组置为3，第三个数组置为4，每个元素在同一个数组里面累加一次，最后大于4的元素为所需元素
        int[] counts = new int[101];

        for (int num : nums1) {
            counts[num] = 2;
        }

        for (int num : nums2) {
            counts[num] = counts[num] >= 3 ? counts[num] : 3 + counts[num];
        }

        for (int num : nums3) {
            if (counts[num] < 4 || counts[num] == 5) {
                counts[num] = 4 + counts[num];
            }
        }

        List<Integer> res = new ArrayList<Integer>();

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 4) {
                res.add(i);
            }
        }


        return res;
    }

}
