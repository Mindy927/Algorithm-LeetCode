/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

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

//Method 1:dfs, preorder
class Solution {
    //preorder: root, right, left
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }
    
    public void dfs(TreeNode root, List<Integer> res, int depth){
        if (root == null) return;
        
        if (depth == res.size()){ //add to result for first(right-most) node in each depth
            res.add(root.val);
        }
        
        dfs(root.right, res, depth+1);
        dfs(root.left, res, depth+1);
    }
}



//Method 2:bfs
class Solution {//bfs
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) return result;
        
        q.offer(root);
        while (!q.isEmpty()){
            int size = q.size();
            for (int i=0; i<size; i++){
                TreeNode node = q.poll();
                if (i==size-1) result.add(node.val);
                if (node.left!=null) q.offer(node.left);
                if (node.right!=null) q.offer(node.right);
            }
        }
        
        return result;    
    }
}

