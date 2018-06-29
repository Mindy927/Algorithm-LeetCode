
/*Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
Author: Mindy927*/

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        List<List<Integer>> result = new ArrayList<>();
        helper(nums, used, new ArrayList<Integer>(), result);
        return result;
    }
    
    public void helper(int[] nums, boolean[] used, List<Integer> temp, List<List<Integer>> result){
        if(temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        
        for (int i=0; i<nums.length; i++){
            if (used[i]) continue;
            //when a number has the same value with its previous, we can use this number only if his previous is used
            if (i>0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            used[i] = true;
            temp.add(nums[i]);
            helper(nums,used, temp, result);
            temp.remove(temp.size()-1);
            used[i] = false;
        }
    }
}