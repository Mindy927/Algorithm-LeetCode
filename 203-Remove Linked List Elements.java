/*

Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5

Author: Mindy927 */


class Solution {
    //Skip cur.next if cur.next,val = val
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        
        while (cur.next!=null){
            if (cur.next.val == val) cur.next = cur.next.next;
            else cur = cur.next;
        }
        
        return dummy.next;
    }
}