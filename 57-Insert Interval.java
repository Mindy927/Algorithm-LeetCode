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
    //Always make sure idx < n before intervals.get(idx)
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int n = intervals.size();
        List<Interval> res = new ArrayList<>();
        
        //add intervals before newInterval
        int idx = 0;
        while ( idx < n && intervals.get(idx).end < newInterval.start) res.add(intervals.get(idx++));
        
        //merge when conflicts (update newInterval and add)
        while ( idx < n && intervals.get(idx).start <= newInterval.end){
            newInterval.start = Math.min(intervals.get(idx).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(idx).end, newInterval.end);
            idx++;
        }
        res.add(newInterval);
        
        //add rest
        while (idx < n) res.add(intervals.get(idx++));
        
        return res;
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