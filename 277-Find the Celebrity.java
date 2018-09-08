/*
Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

Author: Mindy927 */

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

/*
Assume the candidate found in first pass is k
for people 0 to k-1, either current cand or previous can dont know then
for people k+1 to n-1, k dont know them (otherwise they would be swapped as candidate)
Hence, k is the only possible candidate for celebrity
*/
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int cand = 0;
        for (int i=0; i<n; i++){
            if (i == cand) continue;
            if (knows(cand, i)) cand = i;
        }
        
        for (int i=0; i<n ; i++){
            if (i == cand) continue;
            if (!knows(i,cand) || knows(cand,i)) return -1;
        }
        return cand;
    }
}