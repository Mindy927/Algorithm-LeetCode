/*Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:

Input:
    2
   / \
  1   3
Output: true

Author: Mindy927 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Method1: Recursion
class Solution {
    public boolean isValidBST(TreeNode root) {
      return helper(root, null, null);
    }     
    
    public boolean helper(TreeNode root, Integer min, Integer max){ // Integer could be null while int cannot 
        if (root==null) return true;
        if (min!=null && root.val <= min) return false;
        if (max!=null && root.val >= max) return false;

        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }    
}

//Method 2: Iteration using stack
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        
        Integer prev = null;
        while (!stack.isEmpty() || root!=null){
            while(root!= null){ //keep pushing root.left to stack
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            if (prev!=null && root.val <= prev) return false;
            prev = root.val;
            root = root.right;
        }
        
        return true;
    }
}
