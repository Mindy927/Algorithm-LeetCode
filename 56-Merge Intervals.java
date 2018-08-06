/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considerred overlapping.

Author: Mindy927*/

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
    public List<Interval> merge(List<Interval> intervals) {
        //sort interval based on start
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                return a.start - b.start;
            }
        });
        
        List<Interval> result = new ArrayList<>();
        //merge when cur.start < last.end
        for (Interval i:intervals){
            if (result.size()==0 || i.start > result.get(result.size()-1).end) {
                result.add(i);
            } else{
                result.get(result.size()-1).end = Math.max(i.end,result.get(result.size()-1).end);
            } 
        }
        
        return result;
    }
}