/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

Author: Mindy927*/

class Solution {
    //
    //swap the rest to increasing order
    public void nextPermutation(int[] nums) {
        //1. from back to start, find first number k decreased
        int k = nums.length-2;
        while (k>=0){
            if (nums[k] < nums[k+1]) break;
            k--;
        }
        
        //2. from k to end, find smallest number > k, swap
        if (k!=-1) {
            int j = k+1;
            while (j<nums.length-1){
                if (nums[k] >= nums[j+1]) break;
                j++;
            }
            swap(nums,k,j); 
        }
        
        //3. reverse from k+1 to end
        int start = k+1, end = nums.length-1;
        while (start<end) swap(nums, start++,end--);
    }
    
    public void swap(int[] nums, int i, int j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
    }
}