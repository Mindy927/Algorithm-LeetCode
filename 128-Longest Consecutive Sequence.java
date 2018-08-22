/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Author:Mindy927*/

class Solution {
    /*
    hash set, two pass, 
    first pass add all numbers to set, 
    second pass keep removing adjacent numbers from set for each number, similar to union find
    */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num:nums) set.add(num);
        
        int max = 0;
        for(int num:nums){
            int up = num + 1;
            while (set.contains(up)){ //keep removing adjacent numbers, similiar to union find
                set.remove(up);
                up++;
            }
            
            int down = num - 1;
            while (set.contains(down)){
                set.remove(down);
                down--;
            }
            
            max = Math.max(max, up - down - 1);
        }
        
        return max;
    }
}