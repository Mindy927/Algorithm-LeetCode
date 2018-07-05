/*
Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL

Author: Mindy927 */

//2 pass
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k==0) return head;
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        dummy.next = head;
        int size = 0;
        
        //1 pass:find list size & tail 
        while (cur.next!=null){
            cur = cur.next;
            size++;
        }
        ListNode tail = cur;
        
        k = k % size;
        if (k==0) return dummy.next;
        
        //2 pass: set new head at pos size - k + 1 and reconnect to to head
        cur = dummy;
        for (int i=0; i< size-k; i++){
            cur = cur.next;
        }
        
        ListNode newHead = cur.next;
        cur.next = null;
        tail.next = dummy.next;
        
        return newHead;
    }
}