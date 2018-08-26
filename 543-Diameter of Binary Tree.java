/*
Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.


Author:Mindy92*/


//recursively call helper func to get max path from current node, update global variable maxLen along the way

class Solution {
    int len = 1;
    public int diameterOfBinaryTree(TreeNode root) {
        maxPath(root);
        return len-1; //len is max number of nodes in the max path
    }
    
    //return max path from root
    public int maxPath(TreeNode root){
        if (root == null) return 0;
        
        int left = maxPath(root.left);
        int right = maxPath(root.right);
        
        //max path go through root 
        len = Math.max(len, left+right+1);
        
        return Math.max(left,right)+1;
    }
}