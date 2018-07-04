/*
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

Example:

Input:  [1,2,1,3,2,5]
Output: [3,5]

Author: Mindy927 */

/*
Key Idea: Separate two unique  nums and use same idea as #136 to find each of them
(1)  XOR whole list to get XOR result(diff) of two unique nums
(2) Use the bit this two nums differ to separate them (right most set bit in diff)

*/
class Solution {
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for (int n:nums)  diff ^= n;  //1 pass to find XOR of two unique numbers
        
        int bit = diff & ~ (diff-1); // 101100 -> diff-1 = 101011 -> ~(diff-1) 010100 
        
        int[] result = new int[2];
        int num1 = 0;
        int num2 = 0;
        //2 pass use bit to separate two unique numbers
        for (int n:nums){
            if ( (n & bit) == 0 ) num1 ^= n;
            else num2 ^=n;
        }
            
        result[0] = num1;
        result[1] = num2;
        
        return result;
    }
}