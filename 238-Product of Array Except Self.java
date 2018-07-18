/*
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

Author: Mindy927*/

/*
Update result array from both directions, keep current product along the way
1. Multiply from left to right 
result[i] = curProd * num[i-1] //product before
2. Multiply from right to left
result[i] *= curProd * num[i+1]
Remark:
Arrays.fill(result, 1);  //initialise
*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, 1);
        //update result array from left to right
        for (int i=1, curProd=1; i<nums.length; i++){
            curProd *= nums[i-1];
            result[i] = curProd;
        }
        
        //update from right to left
        for (int i=nums.length-2, curProd=1; i>=0; i--){
            curProd *= nums[i+1];
            result[i] *= curProd;
        }
        
        return result;
    }
}