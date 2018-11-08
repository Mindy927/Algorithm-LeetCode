/*
We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.



Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
 

Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.

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

 //build graph of values, BFS
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<Integer, List<Integer>> map = new HashMap<>(); //node:neighbors
        Set<Integer> visited = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        buildMap(root, map);
        
        int dist = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(target.val);
        visited.add(target.val);
        
        //BFS
        while (!q.isEmpty() && dist <= K){
            int size = q.size();
            for (int s=0; s<size; s++){
                int cur = q.poll();
                if (dist == K) res.add(cur);
                if (!map.containsKey(cur)) continue;
                for (int nei:map.get(cur)){
                    if (!visited.contains(nei)){
                        visited.add(nei);
                        q.offer(nei);
                    }
                }
            }
            dist++;
        }
        
        return res;
    }
    
    public void buildMap(TreeNode root, Map<Integer, List<Integer>> map){
        if ( root == null ) return;
        
        if (root.left != null){
            if (!map.containsKey(root.val)) map.put(root.val, new ArrayList<>());
            if (!map.containsKey(root.left.val)) map.put(root.left.val, new ArrayList<>());
            map.get(root.val).add(root.left.val);
            map.get(root.left.val).add(root.val);
            buildMap(root.left, map);
        }
        
        if (root.right != null){
            if (!map.containsKey(root.val)) map.put(root.val, new ArrayList<>());
            if (!map.containsKey(root.right.val)) map.put(root.right.val, new ArrayList<>());
            map.get(root.val).add(root.right.val);
            map.get(root.right.val).add(root.val);
            buildMap(root.right, map);
        }
    }
}