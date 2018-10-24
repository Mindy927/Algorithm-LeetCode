/*
Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

 

Example 1:

Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
 

Note:

1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.

Author: Mindy927*/

//recursion, O(n) time
class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Map<Integer, Integer> map = new HashMap<>(); //value:index mapping for postOrder
        for (int i=0; i<post.length; i++) map.put(post[i], i);
        return helper(pre, 0, pre.length-1, post, 0, map);
    }
    
    public TreeNode helper(int[] pre, int preStart, int preEnd, int[] post, int postStart, Map<Integer, Integer> map){
        if (preStart > preEnd) return null;
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart == preEnd) return root;
        
        int leftEnd = map.get(pre[preStart+1]);
        root.left =  helper(pre, preStart+1, leftEnd - postStart + preStart+1, post, postStart, map);
        root.right = helper(pre, leftEnd - postStart + preStart + 2, preEnd, post, leftEnd+1, map);
           
        return root;
    }
}