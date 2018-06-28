/*Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

Example 1:
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.

Author: Mindy927 */

// DFS + Sorting

class Solution {
    public boolean makesquare(int[] nums) {
        Arrays.sort(nums);
        if (nums.length < 4) return false;
        int sum = 0;
        for (int n:nums) sum += n;
        if ( sum%4 != 0) return false;
        
        int len = sum/4;
        int[] remain = new int[]{len,len,len,len};
        return dfs(nums, nums.length-1, remain);  //Optimized with sorting, using longest matchstick first
    }
    
    
    public boolean dfs(int[] nums, int start, int[] remain){
        if (start==-1) {
            if(remain[0]==0 && remain[1]==0 && remain[2]==0 && remain[3]==0) return true;
            return false;
        }
        
        for (int i=0; i<4; i++){
            if (remain[i] - nums[start] < 0) continue;
            remain[i] -= nums[start];
            if (dfs(nums, start-1, remain)) return true; //start-1
            remain[i] += nums[start];
        }
        return false;
    }

}