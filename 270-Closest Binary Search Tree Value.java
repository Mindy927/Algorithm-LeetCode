/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4
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
class Solution {
    int res;
    public int closestValue(TreeNode root, double target) {
        res = root.val;
        helper(root, target);
        return res;
    }
    
    public void helper(TreeNode root, double target){
        if (root == null) return;
        if ( Math.abs(root.val-target) < Math.abs(res - target) ){
            res = root.val;
        }
        
        if (root.val > target) helper(root.left, target);
        if (root.val < target) helper(root.right, target);
    }
}

//archieve
class Solution {
    public int closestValue(TreeNode root, double target) {
        if (root.left == null && root.right == null || root.val == target) return root.val;
        
        if (root.val > target){
            int left = root.left!=null? closestValue(root.left, target):root.val;
            return Math.abs(root.val-target)<Math.abs(target - left)? root.val:left;
        } else{
            int right = root.right!=null? closestValue(root.right, target):root.val;
            return Math.abs(root.val-target)< Math.abs(target - right)? root.val:right;
        }
        
    }
}