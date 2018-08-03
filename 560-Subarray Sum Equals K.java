/*
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

Author: Mindy927*/

class Solution {
    //1 pass with hash map
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); //previous sum:cnt
        int sum = 0,cnt=0;
        for(int i=0; i<nums.length; i++){
            map.put(sum, map.containsKey(sum)? map.get(sum)+1:1);
            sum += nums[i];
            if (map.containsKey(sum-k)) cnt+=map.get(sum-k);
        }
        return cnt;
    }
}