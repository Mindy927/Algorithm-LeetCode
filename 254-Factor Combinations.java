/*
254. Factor Combinations
DescriptionHintsSubmissionsDiscussSolution
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.
Note:

You may assume that n is always positive.
Factors should be greater than 1 and less than n.

Author:Mindy927*/


class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(n, 2, temp, result);
        return result;
    }
    
    public void helper(int n, int start, List<Integer> temp, List<List<Integer>> result){
        if (n==1) {
             //Handle corner case [1] ==> output should be [] instead of [[]]
            if (temp.size()>1) result.add(new ArrayList<>(temp));
            return;
        }
        
        for (int i=start; i<=n; i++){ 
            if (n % i == 0){
                if (temp.size()!=0 && temp.get(temp.size()-1) > i) continue; //Duplicate [2,6] && [6,2]
                temp.add(i);
                helper(n/i, start, temp, result);
                temp.remove(temp.size()-1);
            }
        }
    }
}