/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:

Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.

Author: Mindy927*/

class Solution {
    public int rob(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }
    
    //res[0]:max profit if rob current house, res[1]:max profix not rob current house
    public int[] helper(TreeNode root){
        if (root == null) return new int[2];
        
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        
        int[] res = new int[2];
        res[0] = root.val + left[1] + right[1];
        //if not rob current house, we could either rob OR not rob its child
        res[1] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
        return res;
    }
}