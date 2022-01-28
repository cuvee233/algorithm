package com.weiyi.leetcode.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * desc: 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。给你一个二维整数数组 properties ，其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。
 * <p>
 * 如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attackj > attacki 且 defensej > defensei 。
 * <p>
 * 返回 弱角色 的数量。
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/28
 */
public class NumberOfWeakCharacters {


    //[[1,1],[2,1],[2,2],[1,2]]
    // [2,2][2,1][1,2][1,1]

    public static void main(String[] args) {
        int[][] arrays = {{1, 1}, {2, 1}, {2, 2}, {1, 2}};

        System.out.println(numberOfWeakCharacters(arrays));

    }

    public static int numberOfWeakCharacters(int[][] properties) {
        // 按攻击力逆序排列，攻击力相同按防御力升序，巧妙避开攻击力相同的情况
        Arrays.sort(properties, (a1, a2) -> a1[0] == a2[0] ? a1[1] - a2[1] : a2[0] - a1[0]);

        int ans = 0;

        // 前面最大的防御值,因为攻击力相同的时候防御值是逆序，所以只要防御力大就代表攻击力也大
        int maxDefense = 0;

        for (int i = 0; i < properties.length; i++) {
            if (maxDefense > properties[i][1]) {
                ans++;
            } else {
                maxDefense = Math.max(maxDefense, properties[i][1]);
            }
        }

        return ans;
    }
}
