package com.weiyi.alorithm.greedy;

/**
 * 题目：
 * <p>
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏
 * <p>
 * 最少点灯数量
 * <p>
 * 解题思路：
 * 列举可能性
 * 1.当前位置是'X'，X不用点灯，直接找到当前位置之后的第一个'.'
 * 2.当前位置是'.',后一个节点是'X',当前位置点灯,点灯数+1，跳到cur+2位置
 * 3.当前位置是'.',后一个位置也是'.',后两个位置为'.',点灯数+1，跳到cur+3的位置
 * 4.当前位置是'.'后一个位置也是'.'后两个位置为'X',点灯数+1，跳到cur+3的位置
 *
 * @author weiyi
 * @date 2023-01-09
 */
public class MinLights {

    public int getMinLights(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int ans = 0;
        int idx = 0;
        while (idx < s.length()) {
            if ('.' == s.charAt(idx)) {
                ans++;
                // 最后一个元素，直接结束循环
                if (idx + 1 == s.length()) {
                    break;
                }

                if (s.charAt(idx + 1) == 'X') {
                    idx += 2;
                } else {
                    idx += 3;
                }
            } else {
                idx++;
            }
        }
        return ans;
    }

}
