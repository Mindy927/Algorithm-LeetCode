/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 

Example:

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]
 

Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         / 
        2          
 

2. Now removing the leaf [2] would result in this tree:

          1          
 

3. Now removing the leaf [1] would result in the empty tree:

          []         

Author: Mindy927*/


//Recusion call to get height and update res
//res[i]: all nodes at heigth i

class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        //res[i]: all nodes at height i, 0 for leaf
        List<List<Integer>> res = new ArrayList<>();
        height(root, res);
        return res;
    }
    
    //get height for root and update res
    public int height(TreeNode root, List<List<Integer>> res){
        if (root == null) return -1;
        
        int left = height(root.left, res);
        int right = height(root.right, res);
        int height = 1 + Math.max(left, right); 
        
        if (res.size() < height+1) res.add(new ArrayList<>());
        res.get(height).add(root.val);
        
        return height;
    }
}
