/*
Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?

Author: Mindy927 */

class Solution {
     /*
     fast is n nodes ahead of slow
     remove slow.next
     slow/fast start from dummy node => deal with case when removing 1 element, head 
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head==null) return head;
        //Two nodes, fast is ahead of slow for n nodes
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        int count = n;
        
        while (fast.next!=null){
            fast = fast.next;
            if (count!=0) { //fast runs n nodes ahead of slow
                count--;
                continue; 
            }
            slow = slow.next;
        }
        
        //remove slow.next
        slow.next = slow.next.next;
        return dummy.next;
    }
}

