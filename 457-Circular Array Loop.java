/*
You are given an array of positive and negative integers. If a number n at an index is positive, then move forward n steps. Conversely, if it's negative (-n), move backward n steps. Assume the first element of the array is forward next to the last element, and the last element is backward next to the first element. Determine if there is a loop in this array. A loop starts and ends at a particular index with more than 1 element along the loop. The loop must be "forward" or "backward'.

Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 -> 2 -> 3 -> 0.

Example 2: Given the array [-1, 2], there is no loop.

Note: The given array is guaranteed to contain no element "0".

Can you do it in O(n) time complexity and O(1) space complexity?

Author: Mindy927*/


//Two pointers, fast and slow, reset visited nodes to 0
class Solution {
    public boolean circularArrayLoop(int[] nums) {
        for (int i=0; i<nums.length; i++){
           if (nums[i] == 0) continue;
            
            //two pointers to find circle
            int slow = i, fast = nextIndex(nums, i);
            while (nums[i] * nums[fast] > 0 && nums[i] * nums[nextIndex(nums, fast)]>0){ //move in same direction as i
                if (slow == fast){
                    if (fast == nextIndex(nums, slow)) break; //loop of length 2
                    else return true;
                }
                slow = nextIndex(nums, slow);
                fast = nextIndex(nums, nextIndex(nums, fast));
            }
            
            //set all nodes on the path to 0
            slow = i;
            int val = nums[i];
            while ( nums[slow] * val > 0){
                int next = nextIndex(nums, slow);
                nums[slow] = 0;
                slow = next;
            }
        }
        
        return false;
    }
    
    public int nextIndex(int[] nums, int i){
        int n = nums.length;
        return i + nums[i] < 0? n+(i+nums[i])%n:(i+nums[i])%n;
    }
}