/*
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.

Author: Mindy927*/

class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack; //syschronized with stack, max val before current node in stack, similiar to min stack
    
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }
    
    public void push(int x) {
        pushHelper(x);
    }
    
    public void pushHelper(int x){
        int prevMax = maxStack.isEmpty()? Integer.MIN_VALUE:maxStack.peek();
        int tempMax = Math.max(prevMax, x);  
        stack.push(x);
        maxStack.push(tempMax);
    }
    
    public int pop() {
        maxStack.pop();
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        int maxVal = maxStack.peek();
        Stack<Integer> temp = new Stack<>();
        while (!stack.isEmpty() && stack.peek()!= maxVal){ //tempStack store all numbers after maxVal
            temp.push(stack.pop());
            maxStack.pop();
        }
        stack.pop(); //pop max
        maxStack.pop();
        
        //push back numbers after max
        while (!temp.isEmpty()) pushHelper(temp.pop());
        return maxVal;
    }
}