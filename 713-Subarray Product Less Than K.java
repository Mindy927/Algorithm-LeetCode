/*
Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Note:

0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.

Authour: Mindy927*/

//version 1: sliding window: find max
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int cnt = 0, prod = 1;
        int left = 0, right = 0;
        
        while (right < nums.length){
            prod *= nums[right];
            
            while ( left <= right && prod >= k){ //while(invalid) for findMax case
                prod = prod/nums[left];
                left++;
            }
            //for each right pointer, find left most element such that product from left to right < k
            cnt += right - left + 1;
            right++;
        }
        
        return cnt;
    }
}


//version 2
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // keep max prod window less than k, iterates through j, find max i such that prod[i,j] < k
        int cnt =0, curProd = 1;
    
        for (int i=0, j=0; j<nums.length; j++){
            curProd *= nums[j];
            while (i<=j && curProd >= k){
                curProd /= nums[i++];
            } 
            cnt += j-i+1;
        }
        
        return cnt;
    }
}