/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.

Example 1:

Input: root = [2,1,3], p = 1

  2
 / \
1   3

Output: 2
Example 2:

Input: root = [5,3,6,2,4,null,null,1], p = 6

      5
     / \
    3   6
   / \
  2   4
 /   
1

Output: null

Author: Mindy927*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return root;
        if (root.val <= p.val) return inorderSuccessor(root.right, p);
        
        int max = findMax(root.left);
        if (max <= p.val ) return root;
        else return inorderSuccessor(root.left, p);
    }
    
    public int findMax(TreeNode root){
        if (root == null) return Integer.MIN_VALUE;
        while (root.right!= null){
            root = root.right;
        }
        return root.val;
    }
}