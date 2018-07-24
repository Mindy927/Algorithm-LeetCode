/*

Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]

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
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
      // index for left = index of root - 1
       // index for right = index of root + 1 
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> qIdx = new LinkedList<>(); //index for current tree node
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        q.offer(root);
        qIdx.offer(0);
        
        while (!q.isEmpty()){
            int size = q.size();
            for (int i=0; i<size; i++){
                TreeNode node = q.poll();
                int index = qIdx.poll();
                List<Integer> temp = map.getOrDefault(index, new ArrayList<>());
                temp.add(node.val);
                map.put(index, temp);
         
                if (node.left!=null) {
                    q.offer(node.left); qIdx.offer(index-1);
                }
                if (node.right!=null) {  
                    q.offer(node.right); qIdx.offer(index+1);
                }
            } 
        }
        
        int min = 0, max = 0;//min,max index 
        for (int index:map.keySet()) {
            min = Math.min(min, index);
            max = Math.max(max,index);
        }
        

        for (int i=min; i<=max; i++){
            result.add(map.get(i));
        }
        return result;
    }
}