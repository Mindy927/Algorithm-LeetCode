/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.

Author: Mindy927 */


//Method 1: Greedy. 

class Solution {
    //Iterate nums, curMax is the farthest point that all points in [0, curEnd] can reach. When reaches curEnd, set curEnd to curMax, cnt++
    public int jump(int[] nums) {
        int cnt = 0;
        int curMax = 0;
        int curEnd = 0;
        for (int i=0; i<nums.length-1; i++){ //nums.length-1, since we dont need to jump at last pos
            curMax = Math.max(curMax, i+nums[i]);
            if (i == curEnd){
                curEnd = curMax;
                cnt++;
            }
        }
        
        return cnt;
    }
}


//Method 2: Beats 2%
class Solution {
    //Go back to start, find the the earliest point which can reach current point 
    public int jump(int[] nums) {
        int cnt = 0;
        int cur = nums.length-1;
        while ( cur > 0 ){
            int temp = cur;
            for (int i = cur-1; i>=0; i--){
                if (i + nums[i] >= temp)  cur = i;
            }
            cnt++;
        }
        
        return cnt;
    }
}