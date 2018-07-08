/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

Author: Mindy927 */

/*
Method 1: Stack : stack stores the indices of bars
Push to stack when cur <= stack.peek() since:
— current bar is left bounded by previous bars in stack
Pop from stack when cur > stack.peek() since:
— that the bar at the top of the stack is bounded by the current bar and a previous bar in the stack
*/
class Solution {
    public int trap(int[] height) {
       int result = 0, i = 0;
       Stack<Integer> stack = new Stack<>();
         
       while ( i < height.length) {
           //Compare with height[stack.peek()] NOT stack.peek()
           if (stack.isEmpty() || height[stack.peek()] >= height[i]) stack.push(i++); 
           else {
                int cur = stack.pop();
                if (stack.isEmpty()) continue;
                //distance between ith bar(right boundary) and second last bar in stack(left boundary)
                int dist = i - stack.peek() - 1; 
                int h = Math.min(height[stack.peek()], height[i]) - height[cur];
                result += dist * h;
           }
       }
        
       return result;
    }
}


// Method 2: DP, O(n) / O(n)
class Solution {
    public int trap(int[] height) {
        if (height.length == 0) return 0;
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int result = 0;
        leftMax[0] = height[0];
        rightMax[n-1] = height[n-1];
        
        for (int i=1; i<n; i++){
            leftMax[i] = Math.max(height[i], leftMax[i-1]);  //left boundary
        }
        
        for (int i=n-2; i>=0; i--){
            rightMax[i] = Math.max(height[i], rightMax[i+1]);//right boundary
        }
        
        for (int i=0; i<n; i++){
            result += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        
        return result;
    }
}