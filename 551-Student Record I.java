/*
You are given a string representing an attendance record for a student. The record only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False

Author: Mindy927 */

class Solution {
    public boolean checkRecord(String s) {
        int cntA = 0;
        int cntL = 0;
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            char prev = i-1>=0? s.charAt(i-1):' ';
            if ( c == 'A') cntA++;
            if ( c == 'L'){
                if (prev == 'L') cntL++;
                else cntL = 1;
            }
            if (cntA > 1 || cntL > 2) return false;
        }
        
        return true;
    }
}