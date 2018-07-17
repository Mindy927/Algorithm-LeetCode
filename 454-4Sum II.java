/*

Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0

Author: Mindy927*/


class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map1 = new HashMap<>(); // sum[A,B] : count
        HashMap<Integer, Integer> map2 = new HashMap<>(); // sum[C,D] : count
        
        fillMap(A, B, map1);
        fillMap(C, D, map2);
        
        int result = 0;
        for (int sum1:map1.keySet()){
            if (map2.containsKey(-sum1)) result += map1.get(sum1) * map2.get(-sum1);
        }
        
        return result;
    }
    
    public void fillMap(int[] A, int[] B, HashMap<Integer, Integer> map){
       for (int i=0; i<A.length; i++){
            for (int j=0; j<B.length; j++){
                int sum = A[i] + B[j];
                map.put(sum, map.containsKey(sum)? map.get(sum)+1: 1);
            }
        }
    }
}