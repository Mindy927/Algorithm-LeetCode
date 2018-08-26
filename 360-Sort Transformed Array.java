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
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] res = new int[n];
        int i = 0, j = nums.length-1;

        if (a <= 0){
            int index = 0;
            while ( i<= j ){
                res[index++] = cal(nums[i], a, b, c) < cal(nums[j], a, b, c)? cal(nums[i++], a, b, c):cal(nums[j--], a, b, c);
            }

        }else{
            int index = n-1; //fill res array from back since quad valud of boundary is larger
            while (i <= j) {
                res[index--] = cal(nums[i], a, b, c) > cal(nums[j], a, b, c)? cal(nums[i++], a, b, c):cal(nums[j--], a, b, c);    
            }
        }
        
        return res;
    }
    
    public int cal(int num, int a, int b, int c){
        return a * num * num + b * num + c;
    }
    