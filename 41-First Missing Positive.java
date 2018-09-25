/*
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.

Author: Mindy927*/

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        //1 pass:put each number in its right place, nums[i] keeps value i+1
        for (int i=0; i<n; i++){
            if (nums[i] <= 0 || nums[i] >= n || nums[i] == i+1) continue;
            
            int next = nums[i] - 1; //put current val to index val-1
            while (next >= 0 && next < n && nums[next] != next+1 ){ //similiar to union find   
                int temp = nums[next]-1; //next index to be filled 
                nums[next] = next+1; 
                next = temp;
            }
        }
        
        //2 pass: verify
        for (int i=0; i<n; i++){
            if (nums[i] != i+1) return i+1;
        }
        
        return n+1;
    }
}