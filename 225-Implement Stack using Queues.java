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
Method 1: 2 queues, O(1) push, O(n) pop
- always has 1 queue which is empty
*/
class MyStack {
    Queue<Integer> q1;
    Queue<Integer> q2;
    /** Initialize your data structure here. */
    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        if (!q1.isEmpty()) q1.offer(x);
        else q2.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    
    //O(n) pop, pop last element in non-empty q and add previous element to another queue
    public int pop() {
        if (!q1.isEmpty()){
            while (q1.size()>1) q2.offer(q1.poll());
            return q1.poll();
        }else{
            while (q2.size()>1) q1.offer(q2.poll());
            return q2.poll();
        }
    }
    
    /** Get the top element. */
    public int top() {
        int res = 0;
        if (!q1.isEmpty()){
            while (q1.size()>1) q2.offer(q1.poll());
            res = q1.peek();
            q2.offer(q1.poll());
        }else{
            while (q2.size()>1) q1.offer(q2.poll());
            res = q2.peek();
            q1.offer(q2.poll());
        }
        return res;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}


/*
Method2 : 1 queue, O(n) push, O(1) pop()
push()
— Add new node and move size()-1 nodes after it
*/

class MyStack {
    Queue<Integer> q;
    /** Initialize your data structure here. */
    public MyStack() {
        q = new LinkedList<>();
    }
    
    /** Push element x onto stack. */    
    // O(n) push, push element to the front of the queue(rotate queue)
    public void push(int x) {
        q.offer(x);
        for(int i=0; i<q.size()-1; i++){
            q.offer(q.poll());
        }
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