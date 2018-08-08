/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

Author: Mindy927 */

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, visited, temp, result); 
        return result;
    }
    
    public void dfs(int[] nums, boolean[] visited, List<Integer> temp, List<List<Integer>> result){
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
        }
        
        for (int i=0; i<nums.length; i++){
            if (visited[i]) continue;
            visited[i] = true;
            temp.add(nums[i]);
            dfs(nums, visited, temp, result); 
            temp.remove(temp.size()-1);
            visited[i] = false;
        }
    }
}