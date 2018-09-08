/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/


//version 1
class Solution {
    public void moveZeroes(int[] nums) {
       int j = 0; //pos of index of all non-zeros+1, once found non-zero, swap with nums[j], and non-zero array len+1(j++)
       for (int i=0; i<nums.length; i++){
            if (nums[i] != 0){ //once found non-zero, move forward to j-th pos
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }
}

//version 2
class Solution {
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i=0; i<nums.length; i++){
            if (nums[i]!=0) {
                nums[index++] = nums[i];
            }
        }
        for (int i=index; i<nums.length; i++){
            nums[i] = 0;
        }
    }
}