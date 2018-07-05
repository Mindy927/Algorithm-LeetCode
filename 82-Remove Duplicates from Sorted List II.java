/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5
Example 2:

Input: 1->1->1->2->3
Output: 2->3
Author: Mindy927 */

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode result = dummy;
        ListNode cur = head;
        int count = 1;
        
        while(cur!=null){
            while (cur.next!= null && cur.val==cur.next.val){
                count++;
                cur = cur.next;
            }
            if (count == 1) { //unique number, add to result
                result.next = cur;
                result = result.next;
            } 
            if (cur.next == null){ //reach end, connect result to null
                result.next = null;
            }
            count = 1;
            cur = cur.next;
        }
        
        return dummy.next;
    }
}