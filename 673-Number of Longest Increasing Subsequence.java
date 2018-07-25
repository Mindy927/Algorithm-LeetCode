/*
Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' le

Author: Mindy927*/

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n < 2) return n;
        int[] len = new int[n]; //max len of increasing sub sequence ends at nums[i]
        int[] cnt = new int[n]; //number of such sub sequences
        int maxLen = 1;
        
        Arrays.fill(len, 1);
        Arrays.fill(cnt, 1);
        for (int i=0; i<n; i++){
            int curLen = 1;
            for (int j=0; j<i; j++){ // find num[j] < num[i], we can append nums[i] to form new subsequence
                if (nums[j] < nums[i]) curLen = Math.max(curLen, len[j]+1);
            }
            len[i] = curLen;
            maxLen = Math.max(len[i], maxLen); //len of longest increasing sub sequence
            
            if (len[i] == 1) continue; //current number is a start
            int curCnt = 0;
            for (int j=0; j<i; j++){ //find # num[j] < num[i] with same max curLen
                if (nums[j] < nums[i] && len[j] == len[i]-1 ) curCnt+= cnt[j];
            }
            cnt[i] = curCnt;
        }
        
        int result = 0;
        for (int i=0; i<n;i++){
            if (len[i] == maxLen) result += cnt[i];
        }
        return result;
    }
}