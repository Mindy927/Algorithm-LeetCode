/*
Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99
Author:Mindy927 */



/*
 Use 2-bit counter (c1&c0)  
current   incoming  next
c1 c0        num[i] c1 c0
0 0            0    0 0
0 1            0    0 1 
1 0            0    1 0
0 0            1    0 0            
0 1            1    1 0
     //1 more num[i], counter 01(1) -> 10 (2)
1 0            1    0 0
Consider 
(1)  c0 changes  when new  num[i] comes 
—>   c0 = num[i]  ^ c0
(2)  c1 changes when c0=1 and new num[i] comes
—>   c1 =  (num[i] & c0) ^ c1 (old c1)

Use XOR to indicating bit change   
 => UpdatedValue = Change Condition ^ Old Value

*/

class Solution {  //k = 3, p=1
    public int singleNumber(int[] nums) {
        int c0 = 0, c1 = 0; 
        for (int num:nums){
        	c1 = (num & c0) ^ c1; //Use old value of c0 to update c1 first
   			c0 = num ^ c0;
   			mask = ~(c0 & c1);
   			c1 &= mask;
   			c0 &= mask;
        }
        return c0;
    }
}


/*
Extra: When k = 5, p = 3
k = 5 (101)  mask =  ~(c0 & ~c1 & c2)  
Since p = 3, in binary form p = '011', then p1 = p2 = 1, so we can return either x1 or x2. 
If p = 4, in binary form p = '100', only p3 = 1, which implies we can only return x
*/

