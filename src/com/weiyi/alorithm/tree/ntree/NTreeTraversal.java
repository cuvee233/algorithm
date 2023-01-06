package com.weiyi.alorithm.tree.ntree;

import com.weiyi.leetcode.tree.multi.Node;

import java.util.*;

/**
 * 多叉树遍历
 *
 * @author weiyi
 * @date 2023-01-06
 */
public class NTreeTraversal {

    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> resList = new ArrayList<>();

        Deque<Node> queue = new ArrayDeque<>();
        queue.addFirst(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> depthList = new ArrayList<>(size);
            resList.add(depthList);
            while (size-- > 0) {
                Node node = queue.pollLast();
                depthList.add(node.val);
                if (node.children != null && node.children.size() > 0) {
                    node.children.forEach(queue::addFirst);
                }

            }
        }
        return resList;
    }


}
