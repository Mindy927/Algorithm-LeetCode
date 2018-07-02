/*Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.

Author: Mindy927 */

//Recursion

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/*  Recursion call for k+1 node
1-> 2 -> 3 -> 4   k=3
head      cur   F(k+1)
  
*/
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k==1 || head==null) return head;
        
        ListNode cur = head;
        int cnt = 0;
        while (cur!=null && cnt < k){
            cur = cur.next;
            cnt++;
        }
        
        ListNode next = reverseKGroup(cur,k); //k+1 node
        
        if (cnt < k) return head; //last less than k elements

        while (cnt > 0){
            ListNode temp = head.next;
            head.next = next;
            next = head;
            head = temp;
            cnt--;
        }
        
        return next;
    }
}