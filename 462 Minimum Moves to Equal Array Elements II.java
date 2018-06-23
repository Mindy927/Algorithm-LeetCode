/*Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]

Author: Mindy927 */

//Final value is median, similiar to meeting point #296
class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int i=0, j=n-1, count=0;
        while (i<j){
            count += nums[j] - nums[i];
            i++;j--;
        }
        
        return count;
    }
}