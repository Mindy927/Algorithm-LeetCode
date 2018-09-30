/*

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.


Author: Mindy927*/


//each node stores min val after current node(including), similiar dp
//add new node to head

class MinStack {
    class Node{
        int val;
        int min;//min of list start with current node
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }
    
    Node head; //dummy node
    /** initialize your data structure here. */
    public MinStack() {
        head = new Node(0);
    }
    
    //add new node after dummy node head
    public void push(int x) {
        Node node = new Node(x);
        node.next = head.next;
        node.min = node.next!=null? Math.min(x, node.next.min):x;
        
        head.next = node;
    }
    
    public void pop() {
        head.next = head.next.next;
    }
    
    public int top() {
        return head.next.val;
    }
    
    public int getMin() {
        return head.next.min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */