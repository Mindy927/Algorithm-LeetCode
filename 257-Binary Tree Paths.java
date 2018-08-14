/*
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3

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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
    
        dfs(root, "", result);
        return result;
    }
    
    public void dfs(TreeNode root, String temp, List<String> result){
        if (root == null) {
            return;
        }
        String next = temp.equals("")? temp + root.val: temp + "->" + root.val;
        if (root.left == null && root.right == null){ //only add temp to result when reach end
            result.add(next);
            return;
        }
        
        dfs(root.left, next, result);
        dfs(root.right, next, result);
        
    }
}