/*
Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".

One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.

Your implementation should support following operations:

MyCircularQueue(k): Constructor, set the size of the queue to be k.
Front: Get the front item from the queue. If the queue is empty, return -1.
Rear: Get the last item from the queue. If the queue is empty, return -1.
enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
isEmpty(): Checks whether the circular queue is empty or not.
isFull(): Checks whether the circular queue is full or not.
 

Example:

MyCircularQueue circularQueue = new MycircularQueue(3); // set the size to be 3
circularQueue.enQueue(1);  // return true
circularQueue.enQueue(2);  // return true
circularQueue.enQueue(3);  // return true
circularQueue.enQueue(4);  // return false, the queue is full
circularQueue.Rear();  // return 3
circularQueue.isFull();  // return true
circularQueue.deQueue();  // return true
circularQueue.enQueue(4);  // return true
circularQueue.Rear();  // return 4
 
Note:

All values will be in the range of [0, 1000].
The number of operations will be in the range of [1, 1000].
Please do not use the built-in Queue library.

Author: Mindy927 */
class MyCircularQueue {
    class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
        }
    }
    
    int size;
    int cnt;
    ListNode head;
    ListNode tail;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        size = k;
        cnt = 0;
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = null;
        tail.next = null;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (cnt >= size) return false;
        ListNode node = new ListNode(value);
        if (head.next == null) { //first node
            head.next = node;
            tail.next = node;
        } else{
            tail.next.next = node;
            node.next = head.next;
            tail.next = node;
        }
        cnt++;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (head.next == null) return false;
        if (head.next == tail.next){ //only 1 element
            head.next = null;
            tail.next = null;
        } else{
            head.next = head.next.next;
            tail.next.next = head.next;
        }
        cnt--;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if (head.next == null) return -1;
        return head.next.val;
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if (tail.next == null) return -1;
        return tail.next.val;
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return head.next == null;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return cnt == size;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */