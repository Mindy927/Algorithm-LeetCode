/*
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42

Author:Mindy927*/


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
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        getMaxPath(root);
        return max;
    }
    
    public int getMaxPath(TreeNode root){ //get max sum from root to any of its children
        if (root == null) return 0;
        
        int left = getMaxPath(root.left);
        int right = getMaxPath(root.right);
        
        int tempMax = root.val; //update global max path
        if (left > 0) tempMax += left;
        if (right > 0) tempMax += right;
        max = Math.max(max, tempMax);
        
        int temp = 0;
        if (left > right && left >=0) temp = left;
        else if (right >=0 ) temp = right;
        
        return root.val + temp;
    }
}