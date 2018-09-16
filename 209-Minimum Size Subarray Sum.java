/*
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 

Author: Mindy927*/

//Method 1: O(n), two pointers
//sliding window : right pointer iterate array , increase left pointer when 1 possible solution found
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int i=0, sum = 0;
        int res = Integer.MAX_VALUE;
        
        for (int j=0; j<nums.length; j++){
            sum += nums[j];
            while (sum >=s){ 
                res = Math.min(res, j-i+1); //compare with previous result when sum>=s
                sum -= nums[i]; //move left pointer
                i++;
            }
        }
        
        return res == Integer.MAX_VALUE? 0:res;
    }
}

//Method 2: O(nlgn) binary search:search if a window of size k exists that satisfy the condition
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) return 0;
        int left = 1, right = nums.length;
        while (left + 1 < right){
            int mid = left + (right - left)/2;
            if (valid(s, nums, mid)) right = mid;
            else left = mid;
        }
        return valid(s, nums, left)? left:valid(s, nums, right)? right:0;
    }
    
    //whether there is a subArray with length len that has sum>=s
    public boolean valid(int s, int[] nums, int len){
        int sum = 0;
        for (int i=0; i<len; i++){
            sum += nums[i];
        }
        if (sum >= s) return true;
        
        for(int i=len; i<nums.length; i++){
            sum += nums[i];
            sum -= nums[i - len];
            if (sum >= s) return true;
        }
        
        return false;
    }