/*

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
Author:Mindy927 */


class Solution {
    //reverse second half and insert to first half
    public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        //reverse list after slow
        ListNode secondHalf = reverse(slow.next);
        slow.next = null;
        
        //Insert secondHalf to firstHalf
        merge(head, secondHalf);
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

    //similiar to merge sort, one pointer for result list(cur)
    //two pointers l1, l2 for each list
    public ListNode merge(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1!=null || l2!=null){
            if (l1 != null) {
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;
            }
            if (l2 != null){
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
            }
        } 
        return dummy.next;
    }
}