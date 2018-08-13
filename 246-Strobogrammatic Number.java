/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true
Example 3:

Input:  "962"
Output: false

Author: Mindy927*/

class Solution {
    public boolean isStrobogrammatic(String num) {
        Set<Character> set = new HashSet<>(Arrays.asList('0','1','6','9','8'));
        
        int i = 0, j = num.length()-1;
        while (i<=j){ // == here since last number at center could only be 0,1,8
            char left = num.charAt(i);
            char right = num.charAt(j);
            if (!set.contains(left) || !set.contains(right)) return false;
            if (left == '0' && right != '0') return false;
            if (left == '1' && right != '1') return false;
            if (left == '6' && right != '9') return false;
            if (left == '9' && right != '6') return false;
            i++;j--;
        }
        
        return true;
    }
}