/*
Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6

Author: Mindy927 */

//iteration
class Solution {
    public int countNodes(TreeNode root) {
        int cnt = 0;
        int h = height(root);  //0-indexing for height
        while ( root != null ){
            if (height(root.right) == h-1){ //left subtree is full(has nodes 2^h -1 + 1(root) = 2^h)
                cnt += 1 << h;
                root = root.right;
            }else{
                cnt += 1 << (h-1);
                root = root.left;
            }
            h--;
        }
        
        return cnt;
    }
    
    public int height(TreeNode root){
        return root == null? -1: 1 + height(root.left);
    }
}