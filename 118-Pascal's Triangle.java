/*
Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
Author: Mindy927*/


//row by row
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;
        
        List<Integer> prev = new ArrayList<>(Arrays.asList(1));
        res.add(prev);
        for (int i=2; i<=numRows; i++){ //start from second row
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            for (int j=0; j<i-2; j++){
                cur.add(prev.get(j) + prev.get(j+1));
            }
            cur.add(1);
            res.add(cur);
            prev = cur;
        }
        
        return res;
    }
}