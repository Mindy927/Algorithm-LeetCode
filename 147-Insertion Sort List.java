/*
Sort a linked list using insertion sort.


A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
 
Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4

Author: Mindy927 */


class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = head; //Current largest node in sorted list 
        ListNode cur = head.next;
        tail.next = null;
        
        while (cur!=null){
             if (cur.val >= tail.val){ //connect tail to cur, move tail & cur when cur > tail
                 tail.next = cur;
                 tail = cur;
                 ListNode next = cur.next;
                 cur.next = null; //cur is new tail, set cur.next to null
                 cur = next;
                 continue;
             } else {
                 ListNode next = cur.next;
                 ListNode pos = dummy; //insert cur after pos
                 while (pos.next!=null && pos.next.val < cur.val) pos = pos.next;
                 ListNode temp = pos.next;
                 pos.next = cur;
                 cur.next = temp;
                 cur = next;
             }
        }
        
        return dummy.next;
    }
}
