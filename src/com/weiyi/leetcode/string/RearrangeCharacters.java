package com.weiyi.leetcode.string;

/**
 * 给你两个下标从 0 开始的字符串 s 和 target 。你可以从 s 取出一些字符并将其重排，得到若干新的字符串。
 * <p>
 * 从 s 中取出字符并重新排列，返回可以形成 target 的 最大 副本数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rearrange-characters-to-make-target-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 *
 * @author weiyi
 * @date 2023-01-13
 */
public class RearrangeCharacters {

    public static void main(String[] args) {
        RearrangeCharacters rearrangeCharacters = new RearrangeCharacters();
        System.out.println(rearrangeCharacters.rearrangeCharacters("abbaccaddaeea", "aaaaa"));
    }

    /**
     * 建一个26长度的数组，记录原始字符的所有字符出现字母次数，目标字符串的每个字符到数组里面减去出现的次数，直到无法减去为止
     *
     * @param s      原始字符
     * @param target 目标字符
     * @return 目标字符在原始字符中的副本数量
     */
    public int rearrangeCharacters(String s, String target) {
        int[] letterCounts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            letterCounts[s.charAt(i) - 'a']++;
        }

        int ans = 0;

        while (true) {
            boolean isEnd = false;
            for (int i = 0; i < target.length(); i++) {
                if (letterCounts[target.charAt(i) - 'a'] > 0) {
                    letterCounts[target.charAt(i) - 'a']--;
                } else {
                    isEnd = true;
                    break;
                }
            }
            if (isEnd) {
                break;
            }
            ans++;
        }

        return ans;
    }

}
