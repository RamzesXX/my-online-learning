package com.khanchych.leetcode;

public class MaximumTwinSumLinkedList {

    public int pairSum(ListNode head) {

        int maxTwinSum = Integer.MIN_VALUE;
        if (head == null) {
            return maxTwinSum;
        }
        ListNode reversedFirstHalf = null;
        ListNode tmp;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null) {
            tmp = slow;
            slow = slow.next;
            tmp.next = reversedFirstHalf;
            reversedFirstHalf = tmp;
            fast = fast.next.next;

        }
        tmp = slow;
        slow = slow.next;
        tmp.next = reversedFirstHalf;
        reversedFirstHalf = tmp;

        while (slow != null) {
            maxTwinSum = Math.max(maxTwinSum, (slow.val + reversedFirstHalf.val));
            slow = slow.next;
            reversedFirstHalf = reversedFirstHalf.next;
        }

        return maxTwinSum;
    }
}
