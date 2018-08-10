/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.

Author: Mindy927 */

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    Stack<TreeNode> stack;
    TreeNode cur;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        cur = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        while (cur!=null){
            stack.push(cur);
            cur = cur.left;
        }
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        cur = stack.pop();
        int res = cur.val;
        cur = cur.right;
        return res;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */