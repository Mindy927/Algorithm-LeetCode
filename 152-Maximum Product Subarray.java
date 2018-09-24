/*

Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

Author:Mindy927*/

//dp with prodMin, prodMax
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int res = Integer.MIN_VALUE;
        int prodMin = 1; //deal with negative number
        int prodMax = 1;
        
        for (int num:nums){
            int temp1 = prodMin * num; //min prod ends at current num
            int temp2 = prodMax * num;
            
            //product min could be (1)new start from num, OR (2) num*previous min/max
            prodMin = Math.min(num, Math.min(temp1, temp2));
            prodMax = Math.max(num, Math.max(temp1, temp2));
            
            res = Math.max(res, prodMax);
        }
        
        return res;
    }
}