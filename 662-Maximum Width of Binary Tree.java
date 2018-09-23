/*
Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:
Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:
Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:
Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).

Author:Mindy927*/

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        Map<Integer, int[]> map = new HashMap<>(); //depth:[leftMostIndex, rightMostIdx]
     
        dfs(root, 0, 0, map);
        int res = 0;
        for (int level:map.keySet()){
            int[] temp = map.get(level);
            res = Math.max(res, temp[1] - temp[0] + 1);
        }
        
        return res;
    }
    
    public void dfs(TreeNode root, int level, int index, Map<Integer, int[]> map){
        if (root == null) return;
        if (!map.containsKey(level)) map.put(level, new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
        map.get(level)[0] = Math.min(map.get(level)[0],index);
        map.get(level)[1] = Math.max(map.get(level)[1], index);
        
        dfs(root.left, level+1, 2*index, map);
        dfs(root.right, level+1, 2*index+1, map);
    }
}