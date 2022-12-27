package com.weiyi.leetcode.tree;

import com.weiyi.leetcode.tree.multi.Node;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 多叉树的后序遍历
 *
 * @author weiyi
 * @date 2022/3/12
 */
public class MultiPostorder {

    public List<Integer> postorder(Node root) {
        if(root == null){
            return Collections.emptyList();
        }

        List<Integer> ans = new ArrayList<>();
        // 保存已经遍历过的节点
        Set<Node> set = new HashSet<>();

        Stack<Node> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            Node cur = stack.peek();
            if (cur.children != null && !cur.children.isEmpty() && !set.contains(cur)) {
                // 标记为已经遍历
                for (int i = cur.children.size() - 1; i >= 0; i--) {
                    stack.add(cur.children.get(i));
                }
            } else {
                cur = stack.pop();
                ans.add(cur.val);
            }
            set.add(cur);
        }

        return ans;
    }

}
