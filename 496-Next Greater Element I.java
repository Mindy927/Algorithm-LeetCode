/*

You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.


Author: Mindy927 */

//Method 1: Brute force
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        
        for (int i=0; i<n; i++){
            boolean found = false;
            for (int j=0; j<m; j++){
                if (nums2[j] == nums1[i]) found = true;
                if (found && nums2[j] > nums1[i]){
                    res[i] = nums2[j];
                    break;
                }
            }
        }
        
        return res;
    }
}


/*
Method 2: Stack + hashMap
iterate nums2
push decreasing sequence to stack, when increase(6) pop 1,3,5
record their next greater value as 6 in hashMap
[ 9 6 5 3 1 6] 
*/

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>(); // num: its next greater value
        int[] result = new int[nums1.length];
        
        for (int i=0; i<nums2.length; i++){
           while (!stack.isEmpty() && stack.peek() < nums2[i]){
                    map.put(stack.pop(), nums2[i]);
                }
           stack.push(nums2[i]);
        }
        
        for (int i=0; i<nums1.length; i++){
            result[i] = map.containsKey(nums1[i])? map.get(nums1[i]):-1;
        }
        
        return result;
    }
}