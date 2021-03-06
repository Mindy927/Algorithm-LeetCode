/*Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

Author: Mindy927 */

/*
(1) min[i] keep min element before i (possible 1)
(2) stack keep all valid pattern 2 for num[j], i.e,  > min[j]
Iterate from end of nums, pop all the nums <= min[j] , since the number before j will be > min[j], nums<= min[j] is invalid (possible pattern 2 )
(3) After pop, if left is > nums[j], return true (find 3)
*/
class Solution {
    public boolean find132pattern(int[] nums) {
       if (nums.length<3) return false;
       Stack<Integer> stack = new Stack<>();
       int[] min = new int[nums.length];
       min[0] = nums[0];
        
       for (int i=1; i<nums.length; i++) { 
          min[i] = Math.min(min[i-1], nums[i]); 
       }
        
       for (int j=nums.length-1; j>=0; j--){
           if (nums[j] > min[j]){ 
               // stack keep all valid pattern 2 for num[j], i.e,  > min[j]
               while (!stack.isEmpty() && stack.peek() <= min[j]) stack.pop(); 
               if (!stack.isEmpty() && stack.peek() < nums[j]) {
                   System.out.println(stack.peek());
                   System.out.println(nums[j]);
                   return true;
               }    
               stack.push(nums[j]); 
           } 
       }
        
       return false;
    }
}