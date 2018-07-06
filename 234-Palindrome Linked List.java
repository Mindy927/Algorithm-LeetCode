/*
Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?
Author: Mindy927*/


class Solution {
    //reverse second half and compare
    public boolean isPalindrome(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
       
        
        slow = slow.next;
        slow = reverse(slow); //start of second half   
        //System.out.println(slow.val);
        
        fast = head; 
        while (slow != null){
            if (slow.val != fast.val) return false;
            slow = slow.next;
            fast = fast.next;
        }
        
        return true;
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