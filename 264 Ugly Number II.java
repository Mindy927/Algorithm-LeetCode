/*Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. */

class Solution {
    public int nthUglyNumber(int n) {
         PriorityQueue<Long> q = new PriorityQueue<>();
         q.offer(1l);
        
         for (int i=1; i<n; i++){
            long temp = q.poll();
            while (!q.isEmpty() && q.peek()==temp) q.poll();   
            q.offer(temp*2);
            q.offer(temp*3);
            q.offer(temp*5); 
         }
        
        return q.poll().intValue();
    }
}

