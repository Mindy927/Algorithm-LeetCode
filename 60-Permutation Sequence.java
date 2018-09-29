/*
The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"

Author: Mindy927 */

class Solution {
    // k / (n-1)! get first element
    public String getPermutation(int n, int k) {
        int[] fac = new int[n]; //fac[i]:i!
        fac[0] = 1; 
        for (int i=1; i<n; i++) fac[i] = fac[i-1] * i;
        
        //create a list of numbers to choose from
        List<Integer> nums = new ArrayList<>();
        for (int i=1; i<=n; i++) nums.add(i);
        
        StringBuilder sb = new StringBuilder();
        
        k--; //convert to 0-indexing
        for (int i=1; i<=n; i++){
            int index = k / fac[n-i];
            sb.append(nums.get(index) + "");
            nums.remove(index); //remove from list when used
            k = k % fac[n-i];
        }
        
        return sb.toString();
    }
}


