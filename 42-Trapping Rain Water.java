/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

Author: Mindy927 */

/*
Method 1: Stack  O(n)/ O(n)
stack stores the indices of bars
Push to stack when cur <= stack.peek() since:
— current bar is left bounded by previous bars in stack
Pop from stack when cur > stack.peek() since:
— that the bar at the top of the stack is bounded by the current bar and a previous bar in the stack
*/
class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        
        int res = 0;
        for (int i=0; i<height.length; i++){
            /*pop all the bar bounded by current bar i and previous bar on the stack
              for case [7,4,3,5], when reach 5, pop 3, 4
            */
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]){ 
                int cur = stack.pop();
                if (stack.isEmpty()) continue;
                //distance between ith bar(right boundary) and second last bar in stack(left boundary)
                int dist = i - stack.peek() - 1;
                int h = Math.min(height[stack.peek()], height[i]) - height[cur];
                res += h * dist;
            }
            stack.push(i);
        }
        
        return res;
    }
}


//Method 2: Two pointers, O(n)/O(1)
/*If there is a larger bar at the other side(say right), water trapped is dependent on the current direction(left->right),left++
Once the bar at the other side is lower, we iterate in opposite direction(right->left)
*/
class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length-1;
        int leftMax = 0, rightMax = 0;
        int res = 0;
        
        while (left <= right){
            if (leftMax <= rightMax){
                if (height[left] > leftMax) leftMax = height[left]; 
                else res += leftMax - height[left];//since rightMax is larger, water trapped only depent on leftMax
                left++;//rightMax is larger, water trapped depending on direction left->right
          }else{
            }else {
                if (height[right] > rightMax) rightMax = height[right];
                else res += rightMax - height[right];
                right--;
            }
        }
        
        return res;
    }
}

// Method 3: DP, O(n) / O(n)
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