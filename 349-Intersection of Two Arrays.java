/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.

Author: Mindy927*/


class Solution {
    //sort, two pointers ,similiar idea as merge sort
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> set = new HashSet<>();
        
        int i = 0, j = 0;
        while (i<nums1.length && j<nums2.length){
            if (nums1[i] == nums2[j]) {
                set.add(nums1[i]);
                i++;j++;
            }
            else if (nums1[i] < nums2[j]) i++;
            else j++;
        }
        
        int[] result = new int[set.size()];
        int idx = 0;
        for(int k:set) result[idx++] = k;
        return result;
    }
}