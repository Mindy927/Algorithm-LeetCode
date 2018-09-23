
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
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        
        dfs(n, 1, k,  temp, res);
        return res;
    }
    
    public void dfs(int n, int start, int k, List<Integer> temp, List<List<Integer>> res){
        if (temp.size() == k){
            res.add(new ArrayList<>(temp));
            return;
        } 
        if (start > n) return;  //break ealier
        
        for(int i=start; i<=n; i++){
            temp.add(i);
            dfs(n, i+1, k, temp, res);
            temp.remove(temp.size()-1);
        }
    }
}