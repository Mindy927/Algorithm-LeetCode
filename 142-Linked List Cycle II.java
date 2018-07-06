
/*

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?

Author : Mindy927
*/

public class Solution {
    /*
    — When fast node & slow node meet, move fast node to head.
    — Both of them move 1 step at a time and next meeting point is start of cycle
    */
    public ListNode detectCycle(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode slow = head;
        ListNode fast = head;
        
        while (slow!= null && fast!=null) {
            slow = slow.next;
            if (fast.next!=null) fast = fast.next.next;
            else return null;
            
            if (slow == fast){
                ListNode slow2 = head;
                while (slow != slow2) {
                    slow = slow.next;
                    slow2= slow2.next;
                }
                return slow;
            }
        }
        
        return null;
    }
}