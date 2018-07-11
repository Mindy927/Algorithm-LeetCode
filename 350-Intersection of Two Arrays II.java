/*
iven two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

Author:Mindy927 */
/*
Method 1:
sort, two pointers ,similiar idea as merge sort

Method 2: HashMap
*/

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        
        int i=0, j=0;
        while (i<nums1.length && j<nums2.length){
            if (nums1[i] == nums2[j]){
                list.add(nums1[i]);
                i++; j++;
            }
            else if (nums1[i] < nums2[j]) i++;
            else j++;
        }
        
        int[] res = new int[list.size()];
        for (int k=0; k<list.size(); k++){
            res[k] = list.get(k);
        }
        
        return res;
    }
}