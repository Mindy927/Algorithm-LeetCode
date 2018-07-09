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



class MinStack {
    private class StackNode{
        StackNode next;
        int val;
        int min; //min of list start with current node
        public StackNode(int val, int min){
            this.val = val;
            this.min = min;
        }
        
        public StackNode(){}
    }

    /** initialize your data structure here. */
    StackNode head;
    public MinStack() {
        head = null;
    }
    
    public void push(int x) {
        if (head == null){
            head = new StackNode(x,x);
        } else {
            StackNode newHead = new StackNode(x, Math.min(x, head.min));
            newHead.next = head;
            head = newHead;
        } 
        return;
    }
    
    public void pop() {
        if (head == null) return;
        head = head.next;
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.min;
    }
}