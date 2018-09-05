/*

Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?

Author: Mindy927*/


//iteration
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null; //NOT dummy node !!
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


//recursion
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        //reverse list from head.next, move current head to the end
        ListNode newTail = head.next;
        ListNode newHead = reverseList(newTail);
        newTail.next = head;
        head.next = null;
        
        return newHead;
    }
}