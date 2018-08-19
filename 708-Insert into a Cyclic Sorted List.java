/*
Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. Otherwise, you should return the original given node.

The following example may help you understand the problem better:

 



In the figure above, there is a cyclic sorted list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list.

Author: Mindy927*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
};
*/


class Solution {
    //corner case: insertVal is largest/smallest 
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            head = new Node(insertVal);
            return head;
        }
        
        Node cur = head.next;
        int min = head.val;
        int max = head.val;
        
        //first pass to find max & min
        while (cur != head){
            min = Math.min(cur.val, min);
            max = Math.max(cur.val, max);
            cur = cur.next;
        }
        
        Node prev = head;
        cur = head.next;
        //second pass to insert
        boolean done = false;
        while (!done){
            boolean cond1 = prev.val <= insertVal && insertVal <= cur.val;
            boolean cond2 = (insertVal <= min || insertVal >= max) && prev.val == max;
            if (cond1 || cond2){
                Node insert = new Node(insertVal);
                prev.next = insert;
                insert.next = cur;
                done = true;
            }
            prev = prev.next;
            cur = cur.next;
        }
    
        return head;
    }
}