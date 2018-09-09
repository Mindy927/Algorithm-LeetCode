/*Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

 Author: Mindy927 */

 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Method 1: helper function with start/end node
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return helper(head, null);
    }
    
    public TreeNode helper(ListNode start, ListNode end){ // [start, end)
        if (start == null || start == end) return null;
        if (start.next == end) return new TreeNode(start.val);
        ListNode slow = start;
        ListNode fast = start;
        
        while (fast!= end && fast.next!=end){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        TreeNode root = new TreeNode(slow.val);
        root.left = helper(start, slow);
        root.right = helper(slow.next, end);
        return root;
    }
}

//version 2: find mid, create root, convert left, convert right, return root;
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null)  return new TreeNode(head.val);
            
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        ListNode prev = null;
        
        //find min:slow
        while (fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        TreeNode root = new TreeNode(slow.val);
        //create left subtree
        if (prev!=null){
            prev.next = null; //break prev -> slow
            TreeNode left = sortedListToBST(dummy.next);
            root.left = left;
        }
        //create right subtree
        TreeNode right = sortedListToBST(slow.next);
        root.right = right;
        
        return root;
    }
}