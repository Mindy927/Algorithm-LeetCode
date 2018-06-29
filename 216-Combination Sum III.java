/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]

Author: Mind927 */


class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(k, n, 1, temp, result);
        return result;
    }
    
    public void helper(int k, int remain, int start, List<Integer> temp, List<List<Integer>> result){
        if (temp.size()==k){
            if (remain == 0) result.add(new ArrayList<>(temp));
            return;
        }
        
        for (int i=start; i<=9; i++){
            temp.add(i);
            if (remain-i>=0) helper(k, remain-i, i+1, temp, result);
            temp.remove(temp.size()-1);
        }
    }
}