/*
Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person. 

Person A will NOT friend request person B (B != A) if any of the following conditions are true:

age[B] <= 0.5 * age[A] + 7
age[B] > age[A]
age[B] > 100 && age[A] < 100
Otherwise, A will friend request B.

Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.

How many total friend requests are made?

Example 1:

Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.
Example 2:

Input: [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.
Example 3:

Input: [20,30,100,110,120]
Output: 
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 

Notes:

1 <= ages.length <= 20000.
1 <= ages[i] <= 120.

Author: Mindy927*/


class Solution {
    public int numFriendRequests(int[] ages) {
        int res = 0;
        int[] cnt = new int[121];
        for (int age:ages) cnt[age]++;
        
        for (int i=1; i<121; i++){
            if (cnt[i]==0) continue;
            if (request(i,i)) res+= cnt[i] * (cnt[i]-1); //people of same age send request to each other
            for (int j=i+1; j<121; j++){
                if (cnt[j]==0) continue;
                if (request(i,j)) res+= cnt[i] * cnt[j]; // all people with age i will send request to all people with age j
                if (request(j,i)) res+= cnt[j] * cnt[i]; //all people with age j will send request to i
            }
        }
        
        return res;
    }
    
    public boolean request(int A, int B){
        boolean notReq = (B<= (A*0.5 + 7)) || B > A || (B > 100 && A <100);
        return !notReq;
    }
}