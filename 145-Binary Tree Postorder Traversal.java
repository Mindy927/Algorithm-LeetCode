/*
Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
Author: Mindy927*/

//BFS using stack
class Solution {
    //post order: Left Right Root
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        
        if (root == null) return res;
        stack.push(root);
        
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            res.add(0, cur.val);
            if (cur.left != null ) stack.push(cur.left);
            if (cur.right !=null ) stack.push(cur.right); 
        }
        
        return res;
    }
}