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
        for (int num:nums) diff ^= num; //1 pass to find XOR of two unique numbers
        
        int r = diff & (-diff); //last bit set in diff

        //2 pass use last bit set to separate two unique numbers
        int a = 0, b = 0;
        for (int num:nums){
            if ( (num & r) == 0)  a ^= num; //possible candidate for a with bit not set at r
            else b ^= num; 
        }
     
        return new int[]{a, b};   
    }
}