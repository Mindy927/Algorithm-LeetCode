/*
There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.

Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.

For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.

Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.

If there isn't such day, output -1.

Example 1:
Input: 
flowers: [1,3,2]
k: 1
Output: 2
Explanation: In the second day, the first and the third flower have become blooming.
Example 2:
Input: 
flowers: [1,2,3]
k: 1
Output: -1
Note:
The given array will be in the range [1, 20000].

Author: Mindy927
*/

class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        int n = flowers.length;
        int[] days = new int[n]; //days[i]: the day flower will bloom at pos i
        
        for(int i=0; i<n; i++){
            days[flowers[i]-1] = i+1;
        }
        
        //find two position start, start+k where all the flowers bloom within pos range[start, start+k] is after days[start], days[start+k]
        int start = 0; //start position
        int res = Integer.MAX_VALUE;
        if (start + k + 1 >= n) return -1;
        int max = Math.max(days[start], days[start + k + 1]); 
        for (int i=0; i<n; i++){
            if (i - start > k) res = Math.min(res, max);//Found another valid day
            if (days[i] <= max){ //new start
                if ( i + k + 1 >= n ) break;
                start = i; 
                max = Math.max(days[start], days[start + k + 1]); //next candidate interval[i, i+k+1]
            }
        }
        return res == Integer.MAX_VALUE? -1:res;
    }
}