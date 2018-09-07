

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
        int n = arr.length;
        List<Integer> res = new ArrayList<>();
        if (k==0) return res;
        
        int cnt = 1;
        int index = find(arr, x); 
        if (index == -1) index = x < arr[0]? 0:n-1;
        int left = index, right = index;
        while (cnt < k){
            if (left-1 < 0) right++;
            else if (right+1 >= n) left--;
            else if (left-1>= 0 && right+1 < n){ //compare left-1 with right+1, verify next closest number before moving pointer
                if ( Math.abs(x - arr[left-1]) <= Math.abs(arr[right+1] - x)) left--;
                else right++;
            }
            cnt++;
        }

        for (int i=left; i<=right; i++){
            res.add(arr[i]);
        }
        return res;
        
    }
    
    //find index closest to x
    public int find(int[] arr, int x){
        int left = 0, right = arr.length-1;
        while (left+1< right){
            int mid = left + (right - left)/2;
            if (arr[mid] == x) return mid;
            else if (arr[mid] < x) left = mid;
            else right = mid;
        }
        
        return Math.abs(arr[left]-x) <= Math.abs(arr[right]-1)? left:right;
    }
}