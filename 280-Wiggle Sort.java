/*
iven an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:

Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
Author :Mindy927*/


/*
Method 1: Sort O(nlgn)
sort and  swap element pair wise
1 ,2 ,3, 4, 5, 6 ==> swap (2,3) (4,5)

Method 2:  1-pass swap O(n)
iterate the array, if order is not correct, swap
— order depends of index%2 ==0
*/
class Solution {
    public void wiggleSort(int[] nums) {
        for (int i=0; i<nums.length-1; i++){
            if (i%2==0 && nums[i] > nums[i+1]) swap(nums, i, i+1);
            if (i%2==1 && nums[i] < nums[i+1]) swap(nums, i, i+1);
        }
    }
    
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}