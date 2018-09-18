

/*
Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104
UPDATE (2017/9/19):
The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition to get the latest changes.

Author: Mindy927*/

/*
1. binary search to find index of closest number
2. expand from the closest number found, compare next possible number(left-1, right+1) and move pointer
*/

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        if (k == 0) return res;
        
        int n = arr.length;
        int mid = closest(arr, 0, n-1, x);
        
        //position of candidate at left/right to be selected in next round
        int left = mid-1, right = mid+1; 
        int cnt = 1; //select mid
        while (cnt < k){
            if (left < 0 || (right < n && Math.abs(arr[left] - x) > Math.abs(arr[right] - x))) right++;
            else left--;
            cnt++;
        }
        
        for (int i=left+1; i<right; i++) res.add(arr[i]);
        return res;
    }
    
    //find closet index to target
    public int closest(int[] arr, int left, int right, int target){
        while (left + 1 < right){
            int mid = left + (right - left)/2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] > target) right = mid;
            else left = mid;
        }
        return Math.abs(arr[left] - target) < Math.abs(arr[right] - target)? left:right;
    }
    
}