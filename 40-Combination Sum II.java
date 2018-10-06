/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]

Author: Mindy927*/

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, candidates.length-1, target, temp, res);
        return res;
    }
    
    public void dfs(int[] nums, int end, int remain, List<Integer> temp, List<List<Integer>> res){
        if (remain == 0){
            res.add(new ArrayList<>(temp));
            return;
        }
        
        if (end < 0 || remain < 0) return;
        
        for (int i=end; i>=0; i--){
            if (i<end && nums[i] == nums[i+1]) continue; //skip duplicates
            temp.add(nums[i]);
            dfs(nums, i-1, remain - nums[i], temp, res);
            temp.remove(temp.size()-1);
        }
    }
}