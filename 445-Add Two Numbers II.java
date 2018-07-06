/*

You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

Author: Mindy927*/

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
         Stack<Integer> s1 = new Stack<>();
         Stack<Integer> s2 = new Stack<>();
         Stack<Integer> res = new Stack<>();
         int carry = 0;
        
        while (l1!= null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        
        while (l2!= null){
            s2.push(l2.val);
            l2 = l2.next;
        }
        
        while (!s1.isEmpty() || !s2.isEmpty()){  ///Sum when at least 1 stack is not empty
            int temp1 = s1.isEmpty()? 0:s1.pop();
            int temp2 = s2.isEmpty()? 0:s2.pop();
            int sum = temp1 + temp2 + carry;
            carry = sum/10;
            res.push(sum%10);
        }
        
        if (carry!=0) res.push(carry);
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!res.isEmpty()){
            cur.next = new ListNode(res.pop());
            cur = cur.next;
        }
        return dummy.next;
    }
}