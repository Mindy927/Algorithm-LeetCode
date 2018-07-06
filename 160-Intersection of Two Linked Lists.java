/*
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.

Author: Mindy927 */

/*
Method 1: Two pointer
— when any of the pointer reach end, connect to head of other List
 ie. set pB to headA when pB==null , pA to headB 
 A :  a1 → a2
             lenA          ↘
                                    c1 → c2 → c3
                               ↗          lenC  
B:     b1 → b2 → b3
         lenB
In this way:
pA  walk lenA + lenC +lenB
pB  walk lenB + lenC + lenA

*/

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA==null || headB == null) return null; 
        
        ListNode a = headA;
        ListNode b = headB;
        
        while (a!=b){
            a = a==null? headB: a.next;
            b = b==null? headA: b.next;
        }
        
        return a;
    }
}