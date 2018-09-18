/*
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.

Author:Mindy927*/


/*
Key idea: sort elements and do binary search instead of linear scan
merge sort, divide array into two parts cnt(left, right) = cnt(left, mid) + cnt(mid+1, right) + C, 
C is number of reverse pairs (i,j) such that i in [left, mid], j in [mid+1, right] , calculated using two pointers
*/

//version 1
class Solution { 
    int[] copy; //global array to copy from orginal nums array when we perform merge sort
    public int reversePairs(int[] nums) {
        copy = new int[nums.length];
        return merge(nums, 0, nums.length-1);
    }
    
    //number of reverse pairs in range nums[left] to nums[right]
    public int merge(int[] nums, int left, int right){
        if (left >= right) return 0;
        
        int mid = left + (right - left)/2;
        int cnt = 0;
        
        cnt += merge(nums, left, mid); //nums[left] to nums[mid] is already sorted after calling helper function
        cnt += merge(nums, mid+1, right);
        
        //
        int i = left, j = mid + 1;
        while (i<=mid){ 
            //check boundary of j in the inner while loop
            while (j<=right && nums[i]/2.0 > nums[j]) j++;
            cnt += j - (mid+1);
            i++;
        }
        //cnt number of reverse pair(i,j) with i in range[start, mid], j in range[mid+1, end]
        mergeSort(nums, left, mid, right);
        return cnt;
    }
    
    //nums[left] to nums[mid] are already sored when we call merge above
    public void mergeSort(int[] nums, int left, int mid, int right){
        if (left >= right) return; 
        //make of copy of original array nums, read from copy array and modify nums array
        for (int i=left; i<=right; i++) copy[i] = nums[i]; 
      
        int p1 = left;
        int p2 = mid + 1;
        int index = left;
        while (index <= right){
            //read from copy array for original data
            if (p1 > mid || (p2<= right && copy[p2] <= copy[p1])) nums[index++] = copy[p2++];
            else nums[index++] = copy[p1++];
        }
        
    }
}





//version 1
class Solution {
    public int reversePairs(int[] nums) {
        return merge(nums, 0, nums.length-1);
    }
    
    //merge sort from start to end and return number of reverse pairs in [start, end]
    public int merge(int[] nums, int start, int end){
        if (start >= end) return 0;
        int mid = start + (end - start)/2;
        int cnt = merge(nums, start, mid) + merge(nums, mid+1, end);
        
        int[] sorted = new int[end - start + 1];
        int i= start, j= mid+1; //pointers for reverse pair
        int idx = 0, k = mid+1; //pointers for merge sort
        
        while ( i<= mid ){
            //cnt number of reverse pair(i,j) with i in range[start, mid], j in range[mid+1, end]
            while (j<=end && nums[i]/2.0 > nums[j])  j++;//number of j that cound form reverse pair with current num i
            cnt += j - (mid+1);
            
            //merge sort
            while (k<=end && nums[k] <= nums[i]){ //number of numbers in right half < nums[i]
                sorted[idx++] = nums[k++];
            }
            
            sorted[idx++] = nums[i++]; //increase i
        }
        
        while ( k<=end ) sorted[idx++] = nums[k++];
        
        //copy sorted array to nums
        for (int t=start; t<=end; t++) nums[t] = sorted[t-start];
        
        return cnt;
    }
}