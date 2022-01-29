package com.khanchych.leetcode;

import java.util.*;

public class WordDictionary {
    private static class Node {
        private boolean isTerminal;
        private final Map<Character, Node> children;
        Node() {
            isTerminal = false;
            children = new HashMap<>();
        }
    }

    private final Node dictionary;

    public WordDictionary() {
        dictionary = new Node();
    }

    public void addWord(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        Node currentNode = dictionary;

        for(char ch: word.toCharArray()) {
            Map<Character, Node> currentLetter = currentNode.children;
            if (currentLetter.containsKey(ch)) {
                currentNode = currentLetter.get(ch);
            } else {
                currentNode = new Node();
                currentLetter.put(ch, currentNode);
            }

        }
        currentNode.isTerminal = true;
    }

    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return true;
        }

        if (dictionary.children.isEmpty()) {
            return false;
        }

        return search(word, 0, dictionary);
    }

    private boolean search(String word, int index, Node node) {
        if (word.charAt(index) == '.') {
            boolean found = false;
            for(char ch: node.children.keySet()) {
                found |= search(word, index, node, ch);
                if (found) {
                    break;
                }
            }
            return found;
        } else {
            return search(word, index, node, word.charAt(index));
        }
    }

    private boolean search(String word, int index, Node node, char ch) {
        if(!node.children.containsKey(ch)) {
            return false;
        } else {
            if (index == word.length() - 1) {
                return node.children.get(ch).isTerminal;
            } else {
                return search(word, index + 1, node.children.get(ch));
            }
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        wordDictionary.search("pad"); // return False
        wordDictionary.search("bad"); // return True
        wordDictionary.search(".ad"); // return True
        wordDictionary.search("b.."); // return True

    }
}
