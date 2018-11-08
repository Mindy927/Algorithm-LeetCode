/*
Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?

Example 1:

Input: N = 10, K = 1, W = 10
Output: 1.00000
Explanation:  Alice gets a single card, then stops.
Example 2:

Input: N = 6, K = 1, W = 10
Output: 0.60000
Explanation:  Alice gets a single card, then stops.
In 6 out of W = 10 possibilities, she is at or below N = 6 points.
Example 3:

Input: N = 21, K = 17, W = 10
Output: 0.73278


Author: Mindy927*/


class Solution {
    public double new21Game(int N, int K, int W) {
        if ( K == 0 || K + W <= N ) return 1.0;
        double[] dp = new double[ K + W + 1]; //dp[i]: prob of getting points i = sum(last W dp values)/W
        dp[0] = 1;
        double sum = 1.0; //sum of window size W for preivous W elments(last possible state)
        double res = 0;
        
        for (int i=1; i<=N; i++){
            dp[i] = sum / W;
            
            //dont add dp[i] to sum when reach K, cause current prob will not be carried to next round, stop drawing
            if ( i < K ) sum += dp[i]; 
            else res += dp[i]; 

            if ( i >= W ) sum -= dp[i-W]; //keep window size K
        }
   
        return res;
    }
}