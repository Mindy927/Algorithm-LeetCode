/*
Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.
Author: Mindy927 */


/*
circular array
1254 => 1254 125 (virtual array of length 2n-1)
iterate (2n-1) times while accessing value from original num-> save space

Key Idea: pop all the elements less than current value and update their next greater value as current value
*/

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>(); //stack of indices
        int[] res = new int[n];
        Arrays.fill(res, -1);
        
        for(int i=0; i<2*n-1; i++) {  //virtual circular array of length 2n-1
            int index = i > n-1? i-n:i;  //actual index
            while (!stack.isEmpty() && nums[stack.peek()] < nums[index]){  
                int smaller = stack.pop();
                res[smaller] = nums[index];
            }
            stack.push(index);
            
        }
        return res;
    }
}