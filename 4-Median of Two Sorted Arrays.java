/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

Author:Mindy927*/

class Solution {
    //if (aMid < bMid) Keep [aRight + bLeft] , else Keep [bRight + aLeft]
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        //for [1 2 3], l is 2, r is 2
        //for [1 2 3 4], l is 2, r is 3
        int l = (m + n + 1)/2;
        int r = (m + n + 2)/2;
        
        return (findKth(nums1, 0, nums2, 0, l) + findKth(nums1, 0, nums2, 0, r))/2.0;
    }
    
    public double findKth(int[] nums1, int start1, int[] nums2, int start2, int k){
        //if num1 is exhausted, return kth number in nums2
        if (start1 >= nums1.length) return nums2[start2 + k - 1];
        if (start2 >= nums2.length) return nums1[start1 + k - 1];
        // If k == 1, return the first number
        // Since nums1 and nums2 is sorted, the smaller one among the start point of nums1 and nums2 is the first one
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
        
        int mid1 = Integer.MAX_VALUE, mid2 = Integer.MAX_VALUE;
        if (start1 + k/2 - 1 < nums1.length) mid1 = nums1[start1 + k/2 - 1];  //k/2 elements from start1(including)
        if (start2 + k/2 - 1 < nums2.length) mid2 = nums2[start2 + k/2 - 1];

        //remove k/2 numbers at each iteration, start from start1 + k/2 not start1 + k/2 - 1 
        if (mid1 < mid2){   
            return findKth(nums1, start1 + k/2, nums2,start2,           k - k/2);  //nums1.right + nums2
        }else{
            return findKth(nums1, start1,           nums2, start2+ k/2 , k - k/2); //nums2.right + nums1
        }
            
    }
}