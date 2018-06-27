/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3


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
    public void recoverTree(TreeNode root) {
        if (root==null) return;
        int[] left = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] right = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        
        traverse(root.left, left);
        traverse(root.right, right);
        
        if (left[1] > right[0]){ // Compare for sub tree first!!
            update(root.left, left[1], right[0]);
            update(root.right, right[0], left[1]);
        }else if (root.val < left[1]) {
            update(root.left,left[1],root.val);
            root.val = left[1];
        } else if (root.val > right[0]){
            update(root.right,right[0],root.val);
            root.val = right[0];
        } else {
            recoverTree(root.left);
            recoverTree(root.right);
        }
    }
    
    //Traverse tree to find min/max, val[0] is min, val[1] is max
    public void traverse (TreeNode root, int[] val){
        if (root == null) return;
        val[0] = Math.min(root.val, val[0]);
        val[1] = Math.max(root.val, val[1]);
        
        traverse(root.left, val);
        traverse(root.right, val);
    }
    
    //update node with prev value to target value
    public void update(TreeNode node, int prev, int target){
        if (node==null) return;
        if (node.val == prev) {
            node.val = target;
            return;
        }
        update(node.left, prev, target);
        update(node.right, prev, target);
    }
    
}