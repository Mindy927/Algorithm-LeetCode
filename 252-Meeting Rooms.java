/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

Example 1:

Input: [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: [[7,10],[2,4]]
Output: true

Author: Mindy927 */

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    //sort based on start point
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>(){ //Collections.sort for list
            public int compare(Interval a, Interval b){ 
                return a.start < b.start ? -1:1;
            }
        });
        
        for(int i=1; i<intervals.length; i++){
            if (intervals[i].start < intervals[i-1].end) return false;
        }
        
        return true;
    }
}