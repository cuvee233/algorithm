package com.weiyi.alorithm.tree;

/**
 * 找到中序遍历的后继节点
 * 1.找右子树的最左节点，若没有最左节点，则后继节点为右孩子
 * 2.没有右孩子，则找父节点，递归判断当前节点是否左孩子，若是左孩子，则为后继节点
 *
 * @author weiyi
 * @date 2023-01-07
 */
public class SuccessorNode {

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }


    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return null;
        }
        Node help;

        if (node.right != null) {
            help = node.right;
            while (help.left != null) {
                help = help.left;
            }
            return help;
        }

        if (node.parent != null) {
            help = node;
            // 父节点不为空，并且当前节点不是父节点的左节点，也就是需要找到左节点
            while (help.parent != null && help.parent.right == help) {
                help = help.parent;
            }
            return help.parent;
        }

        return null;
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        // 父节点
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }


}
