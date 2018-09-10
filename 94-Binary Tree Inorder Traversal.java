/*
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?

Author: Mindy927*/

//Method 1 : recursion
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        
        List<Integer> left = inorderTraversal(root.left);
        List<Integer> right = inorderTraversal(root.right);
        
        for (int l:left) res.add(l);
        res.add(root.val);
        for (int r:right) res.add(r);
        
        return res;
    }
}

//Method 2: Iteration
/*
-- push all the cur.left to stack
-- pop(), add to res
-- cur = cur.right
*/
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode cur = root;
        while (cur!=null || !stack.isEmpty()){
            //start from current node, push all nodes smaller than current node to stack
            while (cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            //stack.pop() is the smallest for now, add to res
            cur = stack.pop(); 
            res.add(cur.val);
            //next smallest number is in the right subtree
            cur = cur.right;
        }
        
        return res;   
    }
}
