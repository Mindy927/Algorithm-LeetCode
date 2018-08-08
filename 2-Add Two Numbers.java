/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.

Author: Mindy937 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        
        int carry = 0;
        while (l1!=null || l2!=null){
            int val1 = l1 == null? 0:l1.val; // handle case when once list finish ealier
            int val2 = l2 == null? 0:l2.val;
            int sum = val1 + val2 + carry;
            carry = sum/10;
            ListNode node = new ListNode(sum%10);
            prev.next = node;
            prev = node;
            if (l1!=null) l1 = l1.next;
            if (l2!=null) l2 = l2.next;
        }
        
        if (carry!=0) {
            ListNode node = new ListNode(carry);
            prev.next = node;
        }
        
        return dummy.next;
    }
}