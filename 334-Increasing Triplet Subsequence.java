/*
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.
Author: Mindy927 */


//Method 1 : dp beats 12%
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int n = nums.length;
        int[] left = new int[n]; //smallest number before nums[i]
        int[] right = new int[n]; //bigger number after nums[i]
        
        left[0] = nums[0];
        right[n-1] = nums[n-1];
        for (int i=1; i<n; i++)  left[i] = Math.min(nums[i], left[i-1]);
        for (int i=n-2; i>=0; i--) right[i] = Math.max(nums[i], right[i+1]);
        
        for (int i=0; i<n; i++){
            if (nums[i] > left[i] && nums[i] < right[i]) return true;
        }
        
        return false;
    }
}


//Method 2: keep updating two unique mins

class Solution {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int num:nums){
            if (num <= first) first = num;// "=" must keep two unique mins
            else if (num < second) second  = num;
            else if (num > second) return true;   //careful with num==second case
        } 
        
        return false;
    }
}