/*
Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example:
nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

Result: [3, 9, 15, 33]

nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

Result: [-23, -5, 1, 7]

Author: Mindy927*/


class Solution {
    /*
    two pointers
    --if a > 0, larger val at two boundaries, fill result array from end
    --else, smaller val at two boundaries, fill result array from beginning
    */
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] res = new int[n];
        int left = 0, right = n-1;
        int index = a>0 ? n-1:0; // if a>0, larger value at two boundaries
        
        while (left <= right){
            int leftVal = cal(nums[left], a, b, c);
            int rightVal = cal(nums[right], a, b, c);
            if (a > 0){  
                if (leftVal > rightVal) { //fill with larger val
                    res[index--] = leftVal;
                    left++;
                }else {
                    res[index--] = rightVal;
                    right--;
                }
            }else {
                if (leftVal < rightVal) {
                    res[index++] = leftVal;
                    left++;
                }else {
                    res[index++] = rightVal;
                    right--;
                }
            }
        }
        
        return res;
    }
    
    public int cal(int x, int a, int b, int c){
        return a*x*x + b*x + c;
    }
}