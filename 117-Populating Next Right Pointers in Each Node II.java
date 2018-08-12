/*
Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
Example:

Given the following binary tree,

     1
   /  \
  2    3
 / \    \
4   5    7
After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \    \
4-> 5 -> 7 -> NULL

Author: Mindy927*/

//Method 1: BFS


//Method 2: three pointers, O(1) space
/*
        3(cur)
	  /    \	
   2 (head) 4
*/
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode head = null; //head is start for each level
        TreeLinkNode prev = null; //prev move on the child level of current node
        TreeLinkNode cur = root;
        
        while (cur != null){
            while (cur != null){ //iterate current level
                if (cur.left != null){
                    if (prev != null) prev.next = cur.left;
                    else head = cur.left;
                    prev = cur.left;
                }  
                
                if (cur.right != null){
                    if (prev != null) prev.next = cur.right;
                    else head = cur.right;
                    prev = cur.right;
                }
                
                cur = cur.next;
            }
            
            cur = head; //move to next level
            prev = null;
            head = null;
        }
    }
}