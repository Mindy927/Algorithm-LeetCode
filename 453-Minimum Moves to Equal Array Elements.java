/*Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.

Example:

Input:
[1,2,3]

Output:
3

Explanation:
Only three moves are needed (remember each move increments two elements):

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4] 

Author: Mindy927 */

class Solution {
    public int minMoves(int[] nums) {
        // sum + k (n-1) = n * x = n * (min + k)
        int sum = 0, min = Integer.MAX_VALUE;
        for (int i=0; i<nums.length; i++){
            sum += nums[i];
            min = Math.min(nums[i],min);
        }
        
        return sum - nums.length * min;
    }
}
