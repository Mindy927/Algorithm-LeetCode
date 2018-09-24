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
            k -= index * fac[n-i];
        }
        
        return sb.toString();
    }
}


// archieve: dfs, beats 7%
class Solution {
    int cnt;
    String res;
    public String getPermutation(int n, int k) {
        boolean[] visited = new boolean[n+1]; //visited[i]:whether we've used i
        cnt = k;
        List<Integer> temp = new ArrayList<>();
        res = "";
        dfs(visited, n, temp);
        return res;
    }
    
    public void dfs(boolean[] visited, int n, List<Integer> temp){
        if (temp.size() == n){
            cnt --;
            if (cnt == 0){
                for (int i=0; i<n; i++) res = res + temp.get(i);
            }
            return;
        }
        
        if (cnt < 0) return;
        
        //there is no start index for next choice, we choose any char that hasnt been visited
        for(int i=1; i<=n; i++){
            if (visited[i]) continue;
            temp.add(i);
            visited[i] = true;
            dfs(visited, n, temp);
            visited[i] = false;
            temp.remove(temp.size()-1);
        }
    }  
}