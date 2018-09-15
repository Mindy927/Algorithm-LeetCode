/*

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

Author: Mindy927*/

//iterate first number, two pointers for the rest two numbers, be aware of duplicates  O(n^2)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) return res;
        
        int n = nums.length;
        Arrays.sort(nums);
        for (int i=0; i<n; i++){
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int j = i+1, k = n-1;
            while ( j < k ){
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0){
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k])));
                    j++; k--;
                    while (j<k && nums[j] == nums[j-1]) j++;
                    while (j<k && nums[k] == nums[k+1]) k--;
                }else if (sum > 0) k--;
                else j++;
            }
        }
        
        return res;
    }
}
