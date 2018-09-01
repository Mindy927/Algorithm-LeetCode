/*

You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

Author: Mindy927*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        Stack<ListNode> res = new Stack<>();
        
        while (l1 != null){
            stack1.push(l1);
            l1 = l1.next;
        }
        
        while (l2 != null){
            stack2.push(l2);
            l2 = l2.next;
        }
        
        int sum = 0, carry = 0;

        //building result list from least significant bit
        ListNode head = null; 
        while (!stack1.isEmpty()|| !stack2.isEmpty()){
            sum = carry + (stack1.isEmpty()? 0:stack1.pop().val) + (stack2.isEmpty()? 0:stack2.pop().val);
            carry = sum/10;
            ListNode cur = new ListNode(sum%10);
            cur.next = head;
            head = cur;
        }
        if (carry!=0) {
            ListNode cur = new ListNode(carry);
            cur.next = head;
            head = cur;
        }
       
        return head;
    }
}