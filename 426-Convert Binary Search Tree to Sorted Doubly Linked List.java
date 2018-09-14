/*
Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:

 


 
We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.

 


 
Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
Author: Mind927 */


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
 
// O(1) space
class Solution {
    Node prev = null; //tail of left subtree, i.e, node before root
    Node first = null;
    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        helper(root);
        prev.right = first;
        first.left = prev;
        return first;
    }
    
    //in-order travesal to build linked list 
    public void helper(Node root){
        if (root == null) return;
        helper(root.left);
        
        if (prev == null) first = root;
        else {
            prev.right = root;
            root.left = prev;
        }
        prev = root;
        helper(root.right);
    }
}

//in-order traversal using stack, O(n) space
class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        Stack<Node> stack = new Stack<>();
        Node dummy = new Node();
        Node prev = dummy;
        while (!stack.isEmpty() || root!= null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop(); //smallest number
            
            prev.right = root;
            root.left = prev;
            prev = root;
            
            root = root.right;
        }
        
        prev.right = dummy.right;
        dummy.right.left = prev;
        
        return dummy.right;
    }
}