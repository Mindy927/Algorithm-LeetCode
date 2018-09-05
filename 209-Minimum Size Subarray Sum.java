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

//Method 2: binary search:search if a window of size k exists that satisfy the condition
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int i = 1, j = nums.length, min = 0;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (windowExist(mid, nums, s)) {
                j = mid - 1;
                min = mid;
            } else i = mid + 1;
        }
        return min;
    }


    private boolean windowExist(int size, int[] nums, int s) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i >= size) sum -= nums[i - size];
            sum += nums[i];
            if (sum >= s) return true;
        }
        return false;
    }
}