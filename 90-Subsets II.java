/*Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

Author: Mindy927 */


class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        helper(nums, 0, result, new ArrayList<Integer>());
        return result;
    }
    
    //helper add all subsets starting from nums[start]
    public void helper(int[] nums, int start, List<List<Integer>> result, List<Integer> temp){
        result.add(new ArrayList<>(temp));
        if(start==nums.length) return;
        
        for (int i= start; i < nums.length; i++){ //i is the start point of next element
            if (i>0 && nums[i] == nums[i-1] && i!=start) continue;
            
            temp.add(nums[i]);
            helper(nums, i+1, result, temp);
            temp.remove(temp.size()-1);  
        }
    }
}