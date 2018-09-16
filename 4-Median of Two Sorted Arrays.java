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

/*
binary search 
imagine the whole array of length m + n, two elements in the middle with pos ( m + n + 1 )/2, ( m + n + 2 )/2 would decide the median
helper function to findKth, each time remove k/2 elements
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int left = ( m + n + 1 )/2;
        int right = ( m + n + 2 )/2;
        
        return (findKth(nums1, 0, nums2, 0, left) +  findKth(nums1, 0, nums2, 0, right))/2.0;
    }
    
    public double findKth(int[] nums1, int start1, int[] nums2, int start2, int k){
        int m = nums1.length, n = nums2.length;
        if ( start1 >= m ) return nums2[ start2 + k - 1];
        if ( start2 >= n ) return nums1[ start1 + k - 1];
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
         
        int mid1 = m-1, mid2 = n-1;
        //move mid pointer for nums1 to k/2th element or end(when k/2>end)
        if (start1 + k/2 - 1 < m) mid1 = start1 + k/2 - 1;
        if (start2 + k/2 - 1 < n) mid2 = start2 + k/2 - 1;
        
        if (nums1[mid1] < nums2[mid2]) {
            return findKth(nums1, mid1 + 1, nums2, start2,   k - (mid1 - start1 + 1)); //start from mid1, remove mid1 - start1 + 1 elements
        } else {
            return findKth(nums1, start1,   nums2, mid2 + 1, k - (mid2 - start2 + 1));
        }
    }
}


//Archieve
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
    
    //we only need start since we need k-th smallest element, for each iteration, we remove k/2 elements
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