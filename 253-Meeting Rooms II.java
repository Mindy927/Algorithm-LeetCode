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
//O(nlgn)

//start critical points(start/end), +1 indicates start, -1 indicates end
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        //sort start/end point, +1 for start, -1 for end
        //keep counter for peak
        int res = 0, cnt = 0;
        
        List<int[]> list = new ArrayList<>();
        for (Interval i:intervals){
            list.add(new int[]{i.start, 1});
            list.add(new int[]{i.end, -1});
        }
        
        Collections.sort(list, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if (a[0] != b[0]) return a[0] - b[0]; //min heap of start/end points
                else return a[1] - b[1]; //start point before end
            }
        });
        
        
        for (int[] i:list){
            cnt += i[1];
            res = Math.max(res, cnt);
        }
        
        return res;
    }
}



//greedy, use meeting room ends first
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        //Sort array based on start points
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                return a.start - b.start;
            }
        });
        
        //min heap based on end points, merge with non-overlapping meeting rooms ends first 
        PriorityQueue<Interval> pq = new PriorityQueue<Interval> ((a,b)->(a.end - b.end)); 
        for (int i=0; i<intervals.length; i++){
            if (pq.isEmpty() || pq.peek().end > intervals[i].start) pq.offer(intervals[i]);
            else {
                Interval prev = pq.poll(); //meeting room ends first
                prev.end = intervals[i].end;
                pq.offer(prev);
            }
        }
        
        return pq.size();
    }
}