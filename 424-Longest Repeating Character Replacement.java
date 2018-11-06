/*
Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.

Author: Mindy927*/

//max sliding window
class Solution {
    //match condition: mostFreqCharCnt + k >= window size
    public int characterReplacement(String s, int k) {
        int[] cnt = new int[26];
        int left = 0, right = 0;
        int maxCnt = 0; //most frequent char in the window
        int res = 0;
        
        while (right < s.length()){
            cnt[ s.charAt(right) - 'A' ]++;
            maxCnt = Math.max(maxCnt, cnt[s.charAt(right) - 'A']);
            
            //update res when valid
            if (right - left + 1 <= maxCnt + k)  
                res = Math.max(res, right - left + 1);
            
            //move left pointer to make valid again
            while ( right - left + 1 > maxCnt + k){
                cnt[ s.charAt(left) - 'A' ]--;
                left++;
            }
 
            right++;
        }
        
        return res;
    }
}