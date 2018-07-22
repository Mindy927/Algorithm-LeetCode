/*
Given a string, determine if a permutation of the string could form a palindrome.

Example 1:

Input: "code"
Output: false
Example 2:

Input: "aab"
Output: true
Example 3:

Input: "carerac"
Output: true

Author: Mindy927 */


class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] cnt = new int[128];
        
        for (int i=0; i<s.length(); i++){
           cnt[s.charAt(i)]++;
        }
        
        int single = 0;
        for (int k:cnt){
            if (k%2 == 1) single++;
        }
        
        return single <=1;

    }
}