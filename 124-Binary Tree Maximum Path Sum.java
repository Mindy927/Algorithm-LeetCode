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
    Integer max = null; 
    public int maxPathSum(TreeNode root) {
        sumFrom(root);
        return max;
    }
    
    //declare as Integer to prevent overflow
    //get max single path sum from root (parent -> children)
    public Integer sumFrom(TreeNode root){
        if (root == null) return null;
        
        Integer left = sumFrom(root.left);
        Integer right = sumFrom(root.right);
        
        //whether we will use sum from sub trees
        int leftSum = (left != null && left > 0)? left:0;
        int rightSum = (right != null && right > 0)? right:0;
        
         //update global max path sum
        int tempMax = leftSum + rightSum + root.val;
        max = max==null? tempMax:Math.max(tempMax, max);
        
        //return max sum root to either left OR right sub tree
        return Math.max(leftSum, rightSum) + root.val;
    }
}