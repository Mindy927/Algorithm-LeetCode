/*
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5

Author: Mindy927 */

class Solution {
    //2 queues, one for node < x, one for node >= x
    public ListNode partition(ListNode head, int x) {
        if (head == null) return head;
        ListNode dummy1 = new ListNode(0); //head for small
        ListNode small = dummy1;
        ListNode dummy2 = new ListNode(0); //head for large
        ListNode large = dummy2;
        
        while (head!=null){
            if (head.val < x){
                small.next = head;
                small = small.next;
            } else{
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        
        small.next = dummy2.next; //link two queues
        large.next = null;
        return dummy1.next;
    }
}