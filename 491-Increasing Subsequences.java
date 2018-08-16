/*
Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.

Author: Mindy927 */
//dfs with set
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> temp = new ArrayList<>();
        dfs(nums, 0, temp, result);
        return new ArrayList<>(result);
    }
    
    public void dfs(int[] nums, int start, List<Integer> temp, Set<List<Integer>> result){
        if (temp.size()>1) result.add(new ArrayList<>(temp));
        if (start == nums.length) {  
            return;
        }
        
        for (int i=start; i<nums.length; i++){ //possible nums to add in this round
            if (temp.size()==0 || temp.get(temp.size()-1) <= nums[i]){
                temp.add(nums[i]);
                dfs(nums, i+1, temp, result);
                temp.remove(temp.size()-1);
            }
        }
        
    }
}