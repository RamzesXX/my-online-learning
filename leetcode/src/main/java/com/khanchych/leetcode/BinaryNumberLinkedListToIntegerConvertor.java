package com.khanchych.leetcode;

public class BinaryNumberLinkedListToIntegerConvertor {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public int getDecimalValue(ListNode head) {
        int number = 0;
        while (head != null) {
            number = (number << 1) + head.val;
            head = head.next;
        }
        return number;
    }
}
