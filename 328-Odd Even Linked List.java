/*
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:

Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL
Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL

Author: Mindy927 */


class Solution {
    public ListNode oddEvenList(ListNode head) {
        ListNode dummy1 = new ListNode(0);
        ListNode odd = dummy1;
        ListNode dummy2 = new ListNode(0); //dummy node for even list
        ListNode even = dummy2;
        
        while (head!=null){
            odd.next = head;
            odd = head;
            if (head.next == null) break;
            head = head.next;
            even.next = head;
            even = head;
            head = head.next;
        }
        
        odd.next = dummy2.next;
        even.next = null;
        
        return dummy1.next;
    }
}