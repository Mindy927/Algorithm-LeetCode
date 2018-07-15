/*
Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.

Author: Mindy927 */



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 //bfs
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Double> result = new ArrayList<>();
        if (root==null) return result;
        
        q.offer(root);
        
        while(!q.isEmpty()){
            int size = q.size();
            double sum = 0;
            for(int i=0; i<size; i++){
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left!=null) q.offer(node.left);
                if (node.right!=null) q.offer(node.right);
            }
            result.add(sum/size);
        }
        
        return result;
    }
}