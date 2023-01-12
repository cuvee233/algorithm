package com.weiyi.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个字符串 s ，它包含一些括号对，每个括号中包含一个 非空 的键。
 * <p>
 * 比方说，字符串 "(name)is(age)yearsold" 中，有 两个 括号对，分别包含键 "name" 和 "age" 。
 * 你知道许多键对应的值，这些关系由二维字符串数组 knowledge 表示，其中 knowledge[i] = [keyi, valuei] ，表示键 keyi 对应的值为 valuei 。
 * <p>
 * 你需要替换 所有 的括号对。当你替换一个括号对，且它包含的键为 keyi 时，你需要：
 * <p>
 * 将 keyi 和括号用对应的值 valuei 替换。
 * 如果从 knowledge 中无法得知某个键对应的值，你需要将 keyi 和括号用问号 "?" 替换（不需要引号）。
 * knowledge 中每个键最多只会出现一次。s 中不会有嵌套的括号。
 * <p>
 * 请你返回替换 所有 括号对后的结果字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/evaluate-the-bracket-pairs-of-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2023-01-12
 */
public class Evaluate {

    public static void main(String[] args) {
        Evaluate evaluate = new Evaluate();

        List<List<String>> knowledge = new ArrayList<>();
        List<String> pairs = new ArrayList<>();
        pairs.add("name");
        pairs.add("bob ");

        List<String> pairs2 = new ArrayList<>();
        pairs2.add("age");
        pairs2.add(" 3 ");
        knowledge.add(pairs);
        knowledge.add(pairs2);
        System.out.println(evaluate.evaluate("(name)is(age)yearsold", knowledge));
    }

    public String evaluate(String s, List<List<String>> knowledge) {
        // 将knowledge转成map
        Map<String, String> valueMap = new HashMap<>(knowledge.size());
        for (List<String> pair : knowledge) {
            valueMap.put(pair.get(0), pair.get(1));
        }


        StringBuilder sb = new StringBuilder();

        int idx = 0;
        while (idx < s.length()) {

            if (s.charAt(idx) == '(') {
                // 找到下一个右括号坐标
                int end = findEnd(s, idx + 1);

                // 替换
                String subStr = s.substring(idx + 1, end );
                String value = valueMap.getOrDefault(subStr, "?");
                sb.append(value);
                idx = end + 1;
            } else {
                sb.append(s.charAt(idx));
                idx++;
            }
        }
        return sb.toString();
    }

    public int findEnd(String s, int start) {
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                return i;
            }
        }
        return -1;
    }

}
