/*
Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
*/

//Merge sort 
class Solution {
    public ListNode sortList(ListNode head) {
        if (head==null || head.next==null) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        
        //Step 1: separate list 
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode right = sortList(slow.next);
        slow.next = null;
        ListNode left = sortList(head);
        
        //Step 2: merge 
        ListNode cur = dummy;
        while (right!=null || left!=null){
            if (right == null || (left!=null && left.val < right.val)) {
                cur.next = left;
                left = left.next;
            }
            else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        
        return dummy.next;
    }
}