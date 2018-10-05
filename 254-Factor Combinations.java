/*
Input: 1
Output: []
Example 2:

Input: 37
Output:[]
Example 3:

Input: 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
Example 4:

Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]

Author: Mindy927*/


class Solution {
    public List<List<Integer>> getFactors(int n) { 
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs(n, 2, temp, res);
        return res;
    }
    
    public void dfs(int remain, int start, List<Integer> temp, List<List<Integer>> res){
        if (remain < start) {
            //make sure temp.size()>1 since we need at least two factors
            if (remain == 1 && temp.size()>1) res.add(new ArrayList<>(temp));
            return;
        }
        
        for (int i=start; i<=remain; i++){
            if (remain % i != 0) continue;
            temp.add(i);
            dfs(remain/i, i, temp, res);
            temp.remove(temp.size()-1);
        }
    }
}