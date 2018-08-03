/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
Author: Mindy927 */

class Solution {
    public boolean isPalindrome(String s) {
        String temp = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        int i = 0, j = temp.length()-1;
        while (i<j){
            if (i<j && temp.charAt(i) != temp.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }

}