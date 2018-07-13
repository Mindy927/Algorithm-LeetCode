/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2

Author: Mindy927 */

/*
2 queue  1 2 3 Median 5 6 7
Left (1 2 3) Max Heap 
Right(5 6 7) Min Heap
Remember to balance left&right heap 
*/
class MedianFinder {
    /** initialize your data structure here. */
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;
    public MedianFinder() {
        left = new PriorityQueue<>((a,b) -> (b-a));
        right = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if (left.isEmpty()) {
            left.offer(num);
            return;
        }
        if (num <= left.peek()) left.offer(num);
        else right.offer(num);
        
        //balance 2 queues
        if (left.size() < right.size()) left.offer(right.poll()); 
        if (left.size() > right.size()+1) right.offer(left.poll());
        
    }
    
    public double findMedian() {
        if (left.size() == right.size()) return (left.peek()+right.peek() + 0.0)/2;
        else return left.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */