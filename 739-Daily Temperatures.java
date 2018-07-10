/*
Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].

Author: Mindy927*/


class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>(); //stack of indices
        int[] res = new int[temperatures.length];
        
        for(int i=0; i<temperatures.length; i++){
            if (stack.isEmpty() || temperatures[stack.peek()] >= temperatures[i]) stack.push(i);
            else{
                //pop all the temp[k] < temp[i] and record i-k as # days they need to wait
                while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){ 
                   int prev = stack.pop();
                   res[prev] = i-prev;
                }
                stack.push(i);
            } 
        }
        
        return res;
    }
}