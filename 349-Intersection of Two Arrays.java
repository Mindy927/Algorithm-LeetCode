/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.

Author: Mindy927*/

/* Follow Up: what if one array m is much larger than n (m >> n)
  Sort small arrays first(nlgn) and binary search for each element in large array(mlgn) => (m+n)lgn
*/
//Method1 : O(nlgn)
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

//Method 2: O(n)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int num:nums1) set1.add(num);
        for (int num:nums2) {
            if (set1.contains(num)) set.add(num);
        }
        
        int[] res = new int[set.size()];
        int i=0;
        for (int num:set) res[i++] = num;
        return res;
    }
}


//Method 3: Binary Search
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (Integer num : nums1) {
            if (binarySearch(nums2, num)) {
                set.add(num);
            }
        }
        int i = 0;
        int[] result = new int[set.size()];
        for (Integer num : set) {
            result[i++] = num;
        }
        return result;
    }
    
    public boolean binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }
}