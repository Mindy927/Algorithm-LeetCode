/*
Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].

Author:Mindy927*/


class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        
        int cnt = 0;
        //iterate largest number C, two pointers for the A,B, such that A+B>C
        for (int i=nums.length-1; i>=2; i--){
            int left = 0, right=i-1;
            while ( left < right ){
                if (left < right && nums[left] + nums[right] > nums[i]) {
                    cnt += right - left; // first number cound be [left, right-1]
                    right --;
                }
                else left++;
            }
        }
        return cnt;
    }
}