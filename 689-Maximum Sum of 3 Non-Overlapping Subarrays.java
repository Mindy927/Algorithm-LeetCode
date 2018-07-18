/*
In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:
Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
Note:
nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).

Author: Mindy927 */

class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n-k+1];  //sum[i] sum of subArray starts from i
        int[] posL = new int[n-k+1]; //posL[i]: start index for max subArray before i
        int[] posR = new int[n-k+1]; //posR[i]: start index for max subArray after i
        
        
        for (int i=0; i<k; i++){
            sum[0] += nums[i];
        }
        
        for (int i=1; i<n-k+1; i++){
           sum[i] = sum[i-1]-nums[i-1] + nums[i+k-1]; 
        }
        
        //Fill posL & posR
        posL[0] = 0;
        for (int i=1; i<n-k+1; i++){
            posL[i] = sum[i] > sum[posL[i-1]]? i:posL[i-1];
        }

        posR[n-k] = n-k; //last start index for subArray of size k
        for(int i=n-k-1; i>=0; i--){
            posR[i] = sum[i] > sum[posR[i+1]]? i:posR[i+1];
        }
        
        //Search based on middle subArray
        int[] result = new int[3];
        int max = 0;
        for (int i=k; i<= n-2*k; i++) {//middle subArray with start index i
            int l = posL[i-k];  //start index of left subArray
            int r = posR[i+k];
            int curSum = sum[l] + sum[i] + sum[r];
            if ( curSum > max){
                max = curSum;
                result[0] = l; result[1] = i; result[2] = r;
            }
        }  
        return result;
        
    }
}