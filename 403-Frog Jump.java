/*

A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

Note:

The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
Example 1:

[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
2 units to the 4th stone, then 3 units to the 6th stone, 
4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.

Author:Mindy927*/


//DFS, check whether we could reach last position with max jump each time (1+2+3.. +n)
class Solution {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) return false;
        int n = stones.length;
        if (n == 1) return true;
        if (stones[1] != 1) return false;
        int last = stones[stones.length-1];
    
        if (last > (n-1) *n /2) return false; //max is 1+2+3+...n 
        
        Set<Integer> set = new HashSet<>(); //set of stone pos
        for (int stone:stones) set.add(stone);        
        return dfs(set, 1, 1, last);
    }
    
    //whether we are able to reach target position if we reach current position with k steps
    public boolean dfs(Set<Integer> set, int cur, int k, int target){
        /*if (!set.contains(cur) || cur > target ) return false; 
        if check set.contains here will cause stack overflow, cause set are copied to children dfs call
        */
        if (cur + k + 1 == target || cur + k == target || cur + k - 1 == target) return true;
        
        if (set.contains(cur + k + 1)){ //check first before recursion, otherwise set will be copied one more time and waste memory
            if (dfs(set, cur + k + 1, k+1, target)) return true;
        }
        
        if (set.contains(cur + k)){
            if (dfs(set, cur + k , k, target)) return true;
        }
        
        if ( k>1 && set.contains(cur + k - 1)){
            if (dfs(set, cur + k - 1, k-1, target)) return true;
        }
        
        return false;
        
    }






//DP, Memory Limit Exceed
class Solution {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        if (stones[1] != 1) return false;
        int len = stones[n-1]; //pos of last stone
        boolean[][] dp = new boolean[len+1][len+1]; // dp[i][j]:jump to stone at pos i with j steps
        
        dp[0][0] = true;
        dp[1][1] = true;
        for (int i=2; i<stones.length; i++){
            int pos = stones[i]; //only need to verify postion with stone
            for (int j=1; j<len+1; j++){
                if ( pos-j > 0) dp[pos][j] = dp[pos-j][j-1] || dp[pos-j][j+1] ||dp[pos-j][j]; //last jump cound be j-1,j,j+1 steps
            }
        }
        
        for (int j=1; j<len+1; j++){
            if (dp[len][j]) return true; //last stone at pos len
        }
        
        return false;
    }
}