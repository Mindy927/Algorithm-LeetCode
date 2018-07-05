/*
Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL

Author: Mindy927 */

class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode start = dummy; //node at pos m-1
        
        for (int i=1; i<m; i++) start = start.next;
        
        ListNode prev = start.next;
        ListNode cur = prev.next;
        int cnt = m;
        
        while (cnt != n){
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur; 
            cur = temp;
            cnt++;
        }
        
        /* 1->2->3->4->5->NULL, m = 2, n = 4
          
           1 -> 2 <- 3 <- 4 -> 5
        start            prev cur
        */
       
        start.next.next = cur; // 2->5
        start.next = prev; // 1->4
        
        return dummy.next;
    }
}
