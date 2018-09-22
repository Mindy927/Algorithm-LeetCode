/*

Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

Example 1:

Input:

   1
    \
     3
    / \
   2   4
        \
         5

Output: 3

Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
Example 2:

Input:

   2
    \
     3
    / 
   2    
  / 
 1

Output: 2 

Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.

Author: Mindy927*/

//dfs
class Solution {
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        helper(root, 0, root.val);
        return max;
    }
    
    public void helper(TreeNode root, int cnt, int prevNum){
        if (root == null) return;
        
        //increase counter for consecutive sequence, otherwise reset to 1
        if (root.val == prevNum + 1) cnt++;
        else cnt = 1;
        
        max = Math.max(max, cnt);
        helper(root.left, cnt, root.val);
        helper(root.right, cnt, root.val);
    }
}