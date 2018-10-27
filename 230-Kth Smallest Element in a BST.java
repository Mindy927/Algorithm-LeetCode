/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

ANS: binary search tree with count of nodes in left subtree

Author: Mindy927*/

// Method 1: in-order traversal
class Solution {
    int cnt = 0;
    Integer res = null;
    public int kthSmallest(TreeNode root, int k) {
        //pre-order traversal
        traverse(root, k);
        return (int) res;
    }
    
    public void traverse(TreeNode root, int k){
        if (root == null || cnt>=k) return;
        
        traverse(root.left, k);
        if (cnt >= k) return;
        cnt++; //count current node
        if (cnt == k){
            res = root.val;
            return;
        }
        traverse(root.right, k);
    }
}

// Method 2: Store cnt in tree nodes, binary search