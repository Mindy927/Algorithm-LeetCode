/*
given two arrays, write a function to compute their intersection.

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
If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, 
read chunks of array that fit into the memory, and record the intersections.

If both nums1 and nums2 are so huge that neither fit into the memory, 
sort them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.
*/


//Method 1:
//sort, two pointers ,similiar idea as merge sort

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

//Method 2: HashMap
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(); //val:cnt
        List<Integer> result = new ArrayList<>();
        
        for (int i=0; i<nums1.length; i++){
            map.put(nums1[i], map.containsKey(nums1[i])? map.get(nums1[i])+1:1);
        }
        
        for (int i=0; i<nums2.length; i++){
            if (map.containsKey(nums2[i])) {
                result.add(nums2[i]);
                if (map.get(nums2[i]) == 1) map.remove(nums2[i]);
                else map.put(nums2[i], map.get(nums2[i])-1);
            }
        }
        
        int[] res = new int[result.size()];
        int i = 0;
        for (int k:result) res[i++] = k;
        return res;
    }
}

