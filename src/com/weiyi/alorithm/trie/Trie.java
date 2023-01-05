package com.weiyi.alorithm.trie;

/**
 * 前缀树
 *
 * @author weiyi
 * @date 2023-01-03
 */
public class Trie {

    public Node node;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        node = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        Node cur = this.node;
        cur.pass++;
        int index;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (cur.nextChars[index] == null) {
                cur.nextChars[index] = new Node();
            }

            cur = cur.nextChars[index];
            cur.pass++;
        }
        cur.end++;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        Node cur = this.node;
        int index;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (cur.nextChars[index] == null) {
                return false;
            }
            cur = cur.nextChars[index];
        }

        return cur.end > 0;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }

        Node cur = this.node;
        int index;
        for (int i = 0; i < prefix.length(); i++) {
            index = prefix.charAt(i) - 'a';
            if (cur.nextChars[index] == null) {
                return false;
            }
            cur = cur.nextChars[index];
        }

        return cur.pass > 0;
    }


    public static class Node {
        /**
         * 使用到当前字符串前缀的字符串数量
         */
        public int pass;
        /**
         * 以当前字符串结尾的前缀字符串数量
         */
        public int end;

        /**
         * 下一个字符树数组
         */
        public Node[] nextChars = new Node[26];
    }

}
