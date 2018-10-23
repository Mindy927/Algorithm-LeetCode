/*
Given a sorted integer array without duplicates, return the summary of its ranges.

Example 1:

Input:  [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.

Example 2:

Input:  [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.

Author: Mindy927*/

//one pass
class Solution {
    public List<String> summaryRanges(int[] nums) {
        int cnt = 1; //length of current consecutive numbers
        List<String> res = new ArrayList<>();
        for (int i=1; i<=nums.length; i++){
           if ( i<nums.length && nums[i] == nums[i-1] + 1 ) cnt++;
           else{ //end of nums or a new start
               if (cnt == 1) res.add(String.valueOf(nums[i-1]));
               else{
                   res.add(nums[i-cnt] + "->" + nums[i-1]);
               }
               cnt = 1;
           }
        }
         
        return res;
    }
}