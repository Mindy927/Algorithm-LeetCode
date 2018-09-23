//450-Delete Node in a BST

/*
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7

Author: Mindy927 */

class Solution {
    //if root.val == key, replace with min value in right sub tree and delete min node in right sub tree recursively
    //if key in left sub tree, need to connect root.left = updated left sub tree
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        if (root.val > key){ 
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {//root.val == key delete root
            if (root.left == null && root.right == null) return null; 
            if (root.left == null) return root.right; 
            if (root.right == null) return root.left;

            //replace root.val with min val in right subtree and delete min node in right subtree
            int min = findMin(root.right);
            root.val = min;
            //calling delteNode(root.right, min) is not enough, need to connect back to root.right
            root.right = deleteNode(root.right, min); 
        }
      
        return root;
    }
    
    public int findMin(TreeNode root){
        while (root.left != null){
            root = root.left;
        }
        return root.val;
        
    }
}