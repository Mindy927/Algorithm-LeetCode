/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
Author: Mindy927 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs(candidates, 0, target, temp, result);
        return result;
    }
    
    public void dfs(int[] candidates, int start, int remain, List<Integer> temp, List<List<Integer>> result){
        if (remain == 0){
            result.add(new ArrayList<>(temp)); return;
        }
        if (remain < 0 ) return;
        for (int i=start; i<candidates.length; i++){
            if (remain - candidates[i] < 0 ) break;
            temp.add(candidates[i]);
            dfs(candidates, i, remain - candidates[i],temp, result);
            temp.remove(temp.size()-1);
        }
    }
}
