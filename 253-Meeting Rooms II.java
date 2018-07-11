/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1

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
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length==0) return 0;
        //Sort array based on start points
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                return a.start - b.start;
            }
        });
        
        //min heap based on end points
        PriorityQueue<Interval> pq = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>(){
           public int compare(Interval a, Interval b){
               return a.end - b.end;
           } 
        });
        
        pq.offer(intervals[0]);
        for (int i=1; i<intervals.length; i++){
            if (intervals[i].start >= pq.peek().end){
                Interval last = pq.poll();
                last.start = Math.min(last.start, intervals[i].start);
                last.end = Math.max(last.end, intervals[i].end);
                pq.offer(last);
            } else {
                pq.offer(intervals[i]);
            }
        }
        
        return pq.size();
         
    }
}