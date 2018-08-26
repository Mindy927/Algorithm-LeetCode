
/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6

Author: Mindy927*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

//Add head nodes to priority queue
//poll node cur, add cur.next to queue

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists ==null ) return null; 
        
        Queue<ListNode> q = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>(){
            public int compare(ListNode a, ListNode b){
                return a.val < b.val ? -1:a.val==b.val? 0:1;
            }
        }); 
        
        for (ListNode node:lists){ //Add head nodes to lists, add when its not null!!
            if (node!=null) q.offer(node); 
        }
        
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        
        while(!q.isEmpty()){
            ListNode node = q.poll();
            if (node.next!=null) q.offer(node.next);
            cur.next = node;
            cur = node;
        }
        return dummy.next;
    }
}