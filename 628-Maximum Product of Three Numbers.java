/*
Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:
Input: [1,2,3]
Output: 6
Example 2:
Input: [1,2,3,4]
Output: 24
Note:
The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.

Author: Mindy927*/

//Method 1: O(nlgn)
//three positive or two negative, 1 positive
class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        
        return Math.max(nums[n-1] * nums[n-2] *nums[n-3], nums[0]*nums[1]*nums[n-1]);
        
    }
}


//Method 2: O(n)
//find three largest numbers and 2 smallest by 1 pass
//three positive or two negative, 1 positive
class Solution {
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE; //min1 is smallest
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE; //max1 is largest
        
        for (int num:nums){
            if (num > max1) { //update three max when larget number found
                max3 = max2; //shift down previous max2 to max3
                max2 = max1;  
                max1 = num; 
            }
            else if (num > max2) {
                max3 = max2;
                max2 = num;
            }
            else if (num > max3) max3 = num;
            
            if (num < min1) {
                min2 = min1;
                min1 = num;
            }
            else if (num < min2) min2 = num;
        }
        
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}