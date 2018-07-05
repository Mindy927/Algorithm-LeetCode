/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3
Author: Mindy927 */

class Solution {
    //update result list when encountering different val or REACH END
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null) return head;
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        ListNode result = cur;
        dummy.next = result;
        
        while (cur!=null){
            cur = cur.next;
            if (cur!=null && cur.val == result.val) continue;
            result.next = cur;
            result = result.next;
        }
        
        return dummy.next;
    }
}