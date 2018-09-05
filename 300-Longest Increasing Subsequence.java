/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?

Author: Mindy927*/

//Method 1: DP O(n^2)
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length==0) return 0;
        int[] dp = new int[nums.length]; //longest increasing subsequence ends at nums[i]
        Arrays.fill(dp,1);
        int max = 1;

        for (int i=1; i<nums.length; i++){
            for (int j=0; j<i; j++){ //subsequence with previous element as j
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[j]+1,dp[i]); 
            }
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}

/*
Method 2: Two pointers O(nlgn)
(1) if x is larger than all tails, append it, increase the size by 1
(2) if tails[i-1] < x <= tails[i], update tails[i], since we can have LIS of length i with smaller tail x
*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] tail = new int[n+1]; //tail[i]:tail of LIS with len i
        int size = 0;
        tail[0] = Integer.MIN_VALUE;
        
        for (int num:nums){
            int left = 0, right = size;
            while (left+1 < right){//binary search for which LIS current number x should fit into
                int mid = left + (right - left)/2;
                if ( num > tail[mid]) left = mid;
                else right = mid;
            }
            
            if (num > tail[left] && num <= tail[right]) tail[right] = num;
            if (num > tail[right]){
                tail[right + 1] = num;
                size++;
            }
        }
        return size;
    }   
}
