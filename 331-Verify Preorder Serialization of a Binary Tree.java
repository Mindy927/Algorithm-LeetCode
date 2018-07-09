/*
One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:

Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
Output: true
Example 2:

Input: "1,#"
Output: false

Author: Mindy927 */


class Solution {
    /*
     we see #, check if the top of stack is also #
     - if so, pop #, pop the number in a while loop, until top of stack is not #
     - if not, push it to stack
     */

    public boolean isValidSerialization(String preorder) {
        Stack<String> stack = new Stack<>();
        String[] nodes = preorder.split(",");
        
        for (String node:nodes){
            if (!node.equals("#")) stack.push(node);
            else {
                while (!stack.isEmpty() && stack.peek().equals("#")){
                    stack.pop(); //second #
                    if (stack.isEmpty()) return false;
                    stack.pop(); //root for two ## 
                }
                stack.push("#");
            }    
        }
    
        return stack.size() == 1 && stack.peek().equals("#");
        
    }
}