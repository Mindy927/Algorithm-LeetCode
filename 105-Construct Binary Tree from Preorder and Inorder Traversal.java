/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7

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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, 0, inorder, 0, inorder.length-1);
    }
    
    public TreeNode helper(int[] preorder, int preStart, int[] inorder, int inStart, int inEnd){
        if (inEnd == inStart) return new TreeNode(inorder[inStart]);
        if (inEnd < inStart) return null;
        
        TreeNode root = new TreeNode(preorder[preStart]);
        int i = inStart;
        while (i <= inEnd && inorder[i]!=root.val) i++;
        
        TreeNode left = helper(preorder, preStart+1, inorder, inStart, i-1);
        TreeNode right = helper(preorder, i - inStart + preStart +1,inorder, i+1, inEnd);
        root.left = left;
        root.right = right;
        return root;  
        
    }
}