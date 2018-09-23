/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7

 Author: Mindy927 */
 
 class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, 0, postorder, 0, postorder.length-1);
    }
    
    public TreeNode helper(int[] inorder, int inStart, int[] postorder, int postStart, int postEnd){
        if (postStart > postEnd) return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        
        if (postStart == postEnd) return root;

        int i = inStart;// find index i of root
        while (inorder[i] != root.val) i++;
        
        root.left = helper(inorder, inStart, postorder, postStart, postStart + i-1 -inStart);
        root.right = helper(inorder, i+1, postorder, postStart + i - inStart, postEnd-1);
        
        return root;
    }
}