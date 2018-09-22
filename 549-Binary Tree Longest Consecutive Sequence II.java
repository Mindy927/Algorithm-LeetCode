/*
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].

Author: Mindy927*/

class Solution {
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        longestFrom(root);
        return max;
    }
    
    //res[0], max decreasing sequence from root
    //res[1], max increasing sequence from root
    public int[] longestFrom(TreeNode root){
        int[] res = new int[2];
        if (root == null) return res;
        Arrays.fill(res, 1);
        
        int[] left = longestFrom(root.left);
        int[] right = longestFrom(root.right);
        
        //update decreasing path
        if (left[0] != 0  && root.val == root.left.val + 1)  
            res[0] = Math.max(res[0], left[0] + 1);
        if (right[0] != 0 && root.val == root.right.val + 1)
            res[0] = Math.max(res[0], right[0] + 1);
        
        //update increasing path
        if (left[1] != 0  && root.val == root.left.val - 1)  
            res[1] = Math.max(res[1], left[1] + 1);
        if (right[0] != 0 && root.val == root.right.val - 1)
            res[1] = Math.max(res[1], right[1] + 1);
        
        //update longest consecutive sequence go through root(leftSubTree + root + rightSubTree)
        max = Math.max(max, res[0] + res[1] - 1);
        
        return res;
    }
}