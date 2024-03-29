package com.weiyi.leetcode.tree.multi;

import java.util.ArrayList;
import java.util.List;

/**
 * 多叉树
 *
 * @author weiyi
 * @date 2022/3/12
 */
public class Node {

    public int val;
    public List<Node> children = new ArrayList<>();

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

}
