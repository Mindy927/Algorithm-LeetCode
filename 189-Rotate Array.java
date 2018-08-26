
/*
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
Note:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?
Author: Mindy927 */


//Method 1: Extra array O(n)  O(n)
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums.length==0) return;
        k = k % nums.length;
        
        int[] res = new int[nums.length];
        for (int i=0; i<nums.length; i++){
            int index = (i+k) % nums.length;
            res[index] = nums[i];
        }
        
        for (int i=0; i<nums.length; i++){
            nums[i] = res[i];
        }
    }
}

//Method 2: Cyclic replacement, counter keeps # numbers have been shifted   time:O(n)  space:O(1)
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums.length == 0) return;
        k = k % nums.length;
        int cnt = 0; //counter keeps # numbers have been shifted
        for (int i=0; cnt < nums.length; i++){
            int prev = nums[i];
            int index = i+k; //index which nums[0] will be swapped to
            while (index!=i){
                int cur = nums[index];
                nums[index] = prev;
                prev = cur;
                index = (index+k) % nums.length;
                cnt++;
            }
            nums[i] = prev;
            cnt++;
        }
    }
}

//Method 3: Reverse all, reverse first k elements, reverse last (n-k) elements
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums.length==0) return;
        k = k % nums.length;
        
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }
    
    public void reverse(int[] nums, int start, int end){
        while (start < end){
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
