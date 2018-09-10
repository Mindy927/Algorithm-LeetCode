/*

Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

 


The largest rectangle is shown in the shaded area, which has area = 10 unit.

 

Example:

Input: [2,1,5,6,2,3]
Output: 10

Author: Mindy927*/

/*
Key Idea:
Since rectangle area grows when height of bar is increasing -> we get better solution when pushing increasing height to stack
When height deceasing for bar i, we need to check current maxArea till i-1, since area grows till i-1
and update global max. (opposite of #42)

Every time we pop, we find out the area of rectangle formed using the current element as the height of the rectangle 
*/
// O(n), O(n)
class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>(); //Keep indices, where heights increases and the area increases
        int i = 0;
        int n = heights.length;
        int max = 0;
        
        while ( i<n ){
            if (stack.isEmpty() ||heights[stack.peek()] <= heights[i]){
                stack.push(i++);
            } else{
                int cur = stack.pop();
                //stack is empty means it reaches the start of the bar, dist = i
                int dist = stack.isEmpty()? i: i - stack.peek() - 1;  // dist between previous index in stack (excluding) and i(excliding), with heights[cur]
                int curMax = dist * heights[cur];
                max = Math.max(max, curMax);
            }
        }
        
        //deal with last bar
        while (!stack.isEmpty()){
            int cur = stack.pop();
            int dist = stack.isEmpty()? n: n - stack.peek() - 1; //n here instead of i
            int curMax = dist * heights[cur];
            max = Math.max(max, curMax);
        }
        
        
        return max;
    }
}