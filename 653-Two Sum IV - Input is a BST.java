/*
Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
Aurhor: Mindy927*/



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//hashset O(n), O(n)
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            int size = q.size();
            for (int i=0; i<size; i++){
                TreeNode cur = q.poll();
                if (set.contains(k-cur.val)) return true;
                set.add(cur.val);
                if (cur.left != null) q.offer(cur.left);
                if (cur.right !=null) q.offer(cur.right);
            }
        }
        return false;    
    }
}

//convert to in-order list and use two pointers
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        int left=0, right=list.size()-1;
        while (left < right){
            int sum = list.get(left) + list.get(right);
            if (sum == k) return true;
            if (sum < k) left ++;
            else right--;
        }
        
        return false;
    }
    
    public void helper(TreeNode root, List<Integer> list){
        if (root == null) return;
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
    }
}
