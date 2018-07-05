/*

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Author: Mindy927*/


/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    /*
    HashMap , 2 pass
    — 1 pass created all nodes, and store <old node, new node>  mapping 
    — 2 pass  add random pointer
    -find new RandomNode based on old random node from map

    Thinking process:
    We need second pass to add random node cause a node may point to node which hasn’t been created yet
    */
    
    public RandomListNode copyRandomList(RandomListNode head) {
        Map<RandomListNode,RandomListNode > map = new HashMap<>();  //< old node, new node >
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode copy = dummy; //copy list
        RandomListNode cur = head; //old list
        
        while ( cur!=null){
            RandomListNode node = new RandomListNode(cur.label);
            map.put(cur, node);
            copy.next = node;
            copy = node;
            cur = cur.next;
        }
        
        cur = head;
        copy = dummy.next;
        while (cur != null){
            copy.random = map.get(cur.random);
            copy = copy.next;
            cur = cur.next;
        }
        
        return dummy.next;
    }
}