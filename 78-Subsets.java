/*
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

Author: Mindy927 */

//Method 1: DFS
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs(nums, 0, temp, result);
        return result;
    }
    
    public void dfs(int[] nums, int start, List<Integer> temp, List<List<Integer>> result){
        result.add(new ArrayList<>(temp)); //for loop are used for adding 1 num, we may not add number in this round, add temp to res first
        if (start == nums.length) return;
        
        for (int i=start; i<nums.length; i++){ //num to add in this round can be any number from start
            //handle duplicate, consider case [1 2 2], add first 2 and second 2 in this round is same
            if (i>0 && nums[i] == nums[i-1] && i!=start) continue; 
            temp.add(nums[i]);
            dfs(nums, i+1, temp, result);
            temp.remove(temp.size()-1);
        }
    }
}

//Method 2:BFS
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        //BFS, for each num, add to previous list(maintain previous list)
        List<Integer> empty = new ArrayList<>();
        res.add(empty);
        for (int i=0; i<nums.length; i++){
            int size = res.size();
            for (int s=0; s<size; s++){
                List<Integer> copy = new ArrayList<>(res.get(s)); //copy and add nums[i]
                copy.add(nums[i]);
                res.add(copy);
            }
        }
        return res;
    }
}