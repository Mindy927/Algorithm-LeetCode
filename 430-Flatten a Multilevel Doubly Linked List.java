/*
You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.

Example:

Input:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL

Author: Mindy927 */

class Solution {
    public Node flatten(Node head) {
        if (head == null) return null;
        Node cur = head;
        
        //use inital state of head.child and head.next to create list to be contatenate later, since head.next would change
        Node child = flatten(head.child);
        Node next = flatten(head.next);
        
        head.child = null; //set child pointer to NULL!
        
        if (child != null){
            head.next = child;
            child.prev = head;
        }
        
        while (child != null){//move to tail of flattened child list
            cur = child;
            child = child.next;
        }

        if (next!=null){ //connect tail of child list to next list
            cur.next = next;
            next.prev = cur;
        }
        
        return head;
    }
    
}