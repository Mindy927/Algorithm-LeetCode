/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]

Author:Mindy927*/

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        List<Integer> temp = new ArrayList<>();
        dfs(root, sum, temp, result);
        return result;
    }
    
    //add to result when reach leaf not null(otherwise add twice for leaf since both subtrees are null)
    public void dfs(TreeNode root, int remain, List<Integer> temp, List<List<Integer>> result){
        if (root == null) return;
        
        if (root.left == null && root.right == null){ //add leaf and remove leaf
            temp.add(root.val);
            if (root.val == remain) result.add(new ArrayList<>(temp)); 
            temp.remove(temp.size()-1);
            return;
        }
        
        temp.add(root.val);
        dfs(root.left, remain - root.val, temp, result);
        dfs(root.right, remain - root.val, temp, result);
        temp.remove(temp.size()-1);
    }
}

