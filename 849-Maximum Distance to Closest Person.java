/*
In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty. 

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 

Return that maximum distance to closest person.

Example 1:

Input: [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.

Example 2:

Input: [1,0,0,0]
Output: 3
Explanation: 
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.

Author: Mindy927 */

//edge cases when people seats at first/last seat
class Solution {
    public int maxDistToClosest(int[] seats) {
        if (seats.length == 0) return 0;
        int cnt = 0;
        int res = 0;
        
        //seats at pos 0, only has person to the right
        int i = 0;
        while (i<seats.length && seats[i++] == 0) cnt++;
        res = cnt;
        
        //seats in middle
        cnt = 0;
        while (i<seats.length){
            if (seats[i] == 0) cnt++;
            else{
                res = Math.max(res, (cnt-1)/2+1);
                cnt = 0;
            }
            i++;
        }
        
        //seats at end, only has person to the left
        if (seats[seats.length-1] == 0) res = Math.max(res, cnt);
        
        return res;
    }
}