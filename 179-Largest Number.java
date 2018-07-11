/*
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"

Author: Mindy927 */

class Solution {
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i=0; i<nums.length; i++){
            strs[i] = String.valueOf(nums[i]);
        }
        
        Arrays.sort(strs, new Comparator<String>(){
           public int compare(String a, String b){
               return (b+a).compareTo(a+b); //Descending order
           } 
        });
        
        if (strs[0].equals("0")) return "0"; //corner case
        
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<strs.length; i++){
            sb.append(strs[i]);
        }
        return sb.toString();
        
    }
}