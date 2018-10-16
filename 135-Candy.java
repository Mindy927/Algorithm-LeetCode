/*
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Example 1:

Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
Example 2:

Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
             The third child gets 1 candy because it satisfies the above two conditions.

Author: Mindy927*/

//similiar to wiggle sort
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] cnt = new int[n];
        Arrays.fill(cnt, 1);
        
        //1 pass from left to right, deal with condition when cur > prev
        for (int i=1; i<n; i++){
            if (ratings[i] > ratings[i-1])  cnt[i] = cnt[i-1] + 1;  
        }
        
        
        //2 pass from right to left, deal with condition when cur > next
        for (int i=n-2; i>=0; i--){
            if (ratings[i] > ratings[i+1]) cnt[i] = Math.max(cnt[i+1]+1, cnt[i]);
        }
        
        int res = 0;
        for (int i=0; i<n; i++) res += cnt[i];
        return res;
    }
}