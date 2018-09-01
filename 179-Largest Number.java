/*
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"

Author: Mindy927 */

class Solution {
    public String largestNumber(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
           public int compare(Integer a, Integer b){
               String order1 = String.valueOf(a) + String.valueOf(b);
               String order2 = String.valueOf(b) + String.valueOf(a);
               return order2.compareTo(order1); //max heap
           } 
        });
        
        for (int num:nums) pq.offer(num);
        
        StringBuilder sb = new StringBuilder();
        if (pq.peek() == 0) return "0"; //corner case[0,0]
        while (!pq.isEmpty()){
            sb.append(pq.poll());
        }
        
        return sb.toString();
    }
}