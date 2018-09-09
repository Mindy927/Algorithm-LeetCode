/*
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.

Author: Mindy927*/


class Solution {
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length()-1;
        while (left < right){
            if (s.charAt(left) != s.charAt(right)){
                return valid(s.substring(0,left) + s.substring(left+1)) || valid(s.substring(0,right) + s.substring(right+1));
            }
            left++;right--;
        }
        return true;
    }
    
    public boolean valid(String s){
        int left = 0, right = s.length()-1;
        while (left < right){
            if (s.charAt(left) != s.charAt(right)) return false;
            left++; right--;
        }
        return true;
    }
}