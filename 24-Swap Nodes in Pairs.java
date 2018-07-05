/*

Given a linked list, swap every two adjacent nodes and return its head.

Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.
Note:

Your algorithm should use only constant extra space.
You may not modify the values in the list's nodes, only nodes itself may be changed.

Author: Mindy927 */

class Solution {
    /*
    update cur.next.next with swapNextTwo (cur.next.next)
    move cur to cur.next.next
   */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = swapNextTwo(head);
        dummy.next = cur;
        
        while (cur!=null && cur.next!=null){
           cur.next.next = swapNextTwo(cur.next.next);
           cur = cur.next.next;
        }
        
        return dummy.next;
    }
    
    public ListNode swapNextTwo(ListNode head){
        if (head == null || head.next==null) return head;
        ListNode next = head.next; 
        head.next = head.next.next;
        next.next = head;
        return next;
    }
}