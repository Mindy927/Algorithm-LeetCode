
/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

Author: Mindy927 */

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        helper(n,k,1, new ArrayList<>(), result);
        return result;
    }
    
    private void helper(int n, int k, int start, List<Integer> temp, List<List<Integer>> result){
        if(temp.size()==k){
            result.add(new ArrayList<>(temp));
            return;
        }
        
        if (start > n) return; //pass i+1 to next round, add to result before checking start
        
        for(int i=start; i<=n; i++){
            temp.add(i);
            helper(n, k, i+1, temp, result);
            temp.remove(temp.size()-1);
        }
    }
}