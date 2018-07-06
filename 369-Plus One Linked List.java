/*
Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3

Output:
1->2->4
Author: Mindy927 */

class Solution {
    // reverse list and keep passing carry to next node
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = reverse(head);
        dummy.next = cur;
        ListNode prev = null;
        
        int carry = 1;
        while (carry!=0 && cur!=null){
            int sum = cur.val + carry;
            cur.val = sum % 10;
            carry = sum / 10;
            if (cur.next == null) prev = cur;
            cur = cur.next;
        }
        
        if (carry!=0) prev.next = new ListNode(carry);
        
        return reverse(dummy.next);
        
    }
       
    public ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode cur = head;
        
        while (cur!=null){
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        
        return prev;
    }
}