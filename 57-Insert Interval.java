/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        
        //add intervals before newInterval
        int i=0;
        while ( i<intervals.size() && intervals.get(i).end < newInterval.start)
            res.add(intervals.get(i++));
        
        //add newInterval
        if (i==intervals.size() || intervals.get(i).start > newInterval.end) res.add(newInterval); //no conflicts
        else {
            //merge
            Interval temp = new Interval( Math.min(intervals.get(i).start, newInterval.start), newInterval.end);
            while ( i<intervals.size() && intervals.get(i).start <= newInterval.end){
                temp.end = Math.max(newInterval.end, intervals.get(i++).end);
            }        
            res.add(temp);
        }

        //add rest
        while (i<intervals.size()) res.add(intervals.get(i++));
        
        return res;
    }
}

//in-place
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        //newInterval before all intervals
        if (intervals.size()==0 || intervals.get(0).start > newInterval.end){
            intervals.add(0, newInterval);
            return intervals;
        }
        
        int index = 0;
        while (index < intervals.size() && intervals.get(index).end < newInterval.start) index++;
        
        //newInterval after all intervals
        if (index == intervals.size()){
            intervals.add(newInterval);
            return intervals;
        }
        
        //merge all overlapping intervals, update start/end point and remove overlapped interval
        int start = Math.min(newInterval.start, intervals.get(index).start);
        int end = newInterval.end;
        while (index < intervals.size() && intervals.get(index).start <= end) { 
            start = Math.min(start, intervals.get(index).start);
            end = Math.max(end, intervals.get(index).end);
            intervals.remove(index);
        }
        
        intervals.add(index, new Interval(start, end));
        
        return intervals;
    }
}