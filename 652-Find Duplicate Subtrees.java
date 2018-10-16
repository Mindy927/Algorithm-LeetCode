/*
Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:

      2
     /
    4
and

    4
Therefore, you need to return above trees' root in the form of a list.

Author: Mindy927 */

class Solution {
    //couter map + serialized subtree string
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();
        serialize(root, map, res);
        return res;
    }
    
    //return string for parent recursion call
    public String serialize(TreeNode root, Map<String, Integer> map, List<TreeNode> res){
        if (root == null) return "#";
        String str = root.val + "," + serialize(root.left, map, res) + "," + serialize(root.right, map, res);
        if (map.getOrDefault(str, 0) == 1) res.add(root);
        map.put(str, map.getOrDefault(str, 0)+1);
        
        return str;
    }
}