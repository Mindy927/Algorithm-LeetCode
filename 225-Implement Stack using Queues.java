/*
implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Example:

MyStack stack = new MyStack();

stack.push(1);
stack.push(2);  
stack.top();   // returns 2
stack.pop();   // returns 2
stack.empty(); // returns false
Notes:

Author: Mindy927*/

/*
Method 1: 2 queue
When pop(), add q.size()-1 to temp q 


Method2 : 1 queue, O(n) push, O(1) pop()
push()
— Add new node and move size()-1 nodes after it
*/


class MyStack {
    Queue<Integer> q;
    /** Initialize your data structure here. */
    int tail;
    public MyStack() {
        q = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) { 
        q.offer(x);
        int size = q.size();
        while (size>1){
            q.offer(q.poll());
            size--;
        }
        return;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q.poll();
    }
    
    /** Get the top element. */
    public int top() {
       return q.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */