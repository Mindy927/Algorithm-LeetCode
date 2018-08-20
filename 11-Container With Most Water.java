/*
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

 



The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

 

Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
Author:Mindy927*/

class Solution {
    //two pointers, move pointer point to short line(only in this way, we could generate better solution)
    public int maxArea(int[] height) {
        int max = 0;
        int i=0, j = height.length-1;
        while (i<j){
            int area = Math.min(height[i], height[j]) * (j-i);
            max = Math.max(area, max);
            if (height[i] < height[j]) i++; 
            else j--;
        }
        
        return max;
    }
}