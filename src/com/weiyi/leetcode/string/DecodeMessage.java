package com.weiyi.leetcode.string;

import java.util.HashMap;

/**
 * 给你字符串 key 和 message ，分别表示一个加密密钥和一段加密消息。解密 message 的步骤如下：
 * <p>
 * 使用 key 中 26 个英文小写字母第一次出现的顺序作为替换表中的字母 顺序 。
 * 将替换表与普通英文字母表对齐，形成对照表。
 * 按照对照表 替换 message 中的每个字母。
 * 空格 ' ' 保持不变。
 * 例如，key = "happy boy"（实际的加密密钥会包含字母表中每个字母 至少一次），据此，可以得到部分对照表（'h' -> 'a'、'a' -> 'b'、'p' -> 'c'、'y' -> 'd'、'b' -> 'e'、'o' -> 'f'）。
 * 返回解密后的消息。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/decode-the-message
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2023-02-01
 */
public class DecodeMessage {

    /**
     * 1.建立码表，加密的字母对应解密的字母
     * 2.遍历message解密
     */
    public String decodeMessage(String key, String message) {

        HashMap<Character, Character> letterMap = new HashMap<>(32);

        for (int i = 0; i < key.length(); i++) {
            char letter = key.charAt(i);
            if (letter != ' ' && !letterMap.containsKey(letter)) {
                letterMap.put(letter, (char) (letterMap.size() + 'a'));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char letter = message.charAt(i);
            if (letter == ' ') {
                sb.append(' ');
            } else {
                sb.append(letterMap.get(letter));
            }
        }

        return sb.toString();
    }

}
