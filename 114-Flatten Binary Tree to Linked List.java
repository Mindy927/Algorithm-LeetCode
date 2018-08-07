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
class Solution {
    //recursion, root -> flattened left subTree -> flattened right subTree

    public void flatten(TreeNode root) {
        helper(root);
    }
    
    public TreeNode helper(TreeNode root){
        if (root == null) return root;
        
        TreeNode left = helper(root.left);
        root.left = null; //set to null
        TreeNode right = helper(root.right);
        
        if (left == null)  {
            root.right = right;
        } else{
           root.right = left;
           while (left.right!=null) left = left.right;
           left.right = right;
        }
        
        return root;
    }
}