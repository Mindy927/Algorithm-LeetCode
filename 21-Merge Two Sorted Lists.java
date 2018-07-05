/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
Author: Mindy927*/

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode cur = null;
        dummy.next = cur;
        
        while (l1!=null || l2!=null){
            if (l1!=null && l2!=null) {
                if (l1.val < l2.val) {
                    cur = new ListNode(l1.val);
                    l1 = l1.next;
                }else{
                    cur = new ListNode(l2.val);
                    l2 = l2.next;
                }
            } else if (l1==null){
                cur = new ListNode(l2.val);
                l2 = l2.next;
            } else{
                cur = new ListNode (l1.val);
                l1 = l1.next;
            }
            prev.next = cur;
            prev = cur;
        }
        
        return dummy.next;
    }
}