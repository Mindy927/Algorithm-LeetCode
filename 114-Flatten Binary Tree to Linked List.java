/*
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6

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

//recursion, root -> flattened left subTree -> flattened right subTree
class Solution {
    public void flatten(TreeNode root) {
        helper(root);
    }
    
    public TreeNode helper(TreeNode root){
        if (root == null) return null;
        
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);
        
        root.left = null;//set to null
        if (left == null){
            root.right = right;
        }else {
            root.right = left;
            while (left.right != null) left = left.right; //move to tail of list get from left subtree
            left.right = right;
        }
        
        return root;
    
    }
}