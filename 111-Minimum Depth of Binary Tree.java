/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.

Author: Mindy927*/

//add to result only when reach a leaf
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int left = root.left != null? minDepth(root.left):Integer.MAX_VALUE;
        int right = root.right != null? minDepth(root.right):Integer.MAX_VALUE;
        return Math.min(left, right) + 1;
    }
}




//archieve
class Solution {
    int min = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        helper(root, 1);
        return min;
    }
    
    public void helper(TreeNode root, int depth){
        if (root == null) return;
        if (root.left == null && root.right == null) { //only update result when reach a leaf
            min = Math.min(depth, min);
            return;
        }
        helper(root.left, depth+1);
        helper(root.right, depth+1);
    }      
}