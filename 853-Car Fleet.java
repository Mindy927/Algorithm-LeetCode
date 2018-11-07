/*
N cars are going to the same destination along a one lane road.  The destination is target miles away.

Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.

A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.

The distance between these two cars is ignored - they are assumed to have the same position.

A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that a single car is also a car fleet.

If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.


How many car fleets will arrive at the destination?

 

Example 1:

Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
Output: 3
Explanation:
The cars starting at 10 and 8 become a fleet, meeting each other at 12.
The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
The cars starting at 5 and 3 become a fleet, meeting each other at 6.
Note that no other cars meet these fleets before the destination, so the answer is 3.

Note:

0 <= N <= 10 ^ 4
0 < target <= 10 ^ 6
0 < speed[i] <= 10 ^ 6
0 <= position[i] < target
All initial positions are different.


Author: Mindy927
*/

//verion 1
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        List<double[]> list = new ArrayList<>();
        int n = position.length;
        
        //list(i)[0]:pos
        //list(i)[1]:time reach target
        for (int i=0; i<n; i++){
            list.add(new double[]{(double)(target-position[i]), (double)(target -position[i])/(double)speed[i]});
        }
        
        //sort by position, closest to furthest
        Collections.sort(list, new Comparator<double[]>(){
            public int compare(double[] a, double[] b){
                return (int)(a[0] - b[0]);
            }
        });
        
        double prev = 0;
        int cnt = 0;
        for(int i=0; i<n; i++){
            if (list.get(i)[1] > prev ){ //start a new cluster if it reaches later
                cnt++;
                prev = list.get(i)[1];
            }
        }
        
        return cnt;
    }
}


//version 2 tree map
public int carFleet(int target, int[] pos, int[] speed) {
        TreeMap<Integer, Double> m = new TreeMap<>();
        for (int i = 0; i < pos.length; ++i) m.put(-pos[i], (double)(target - pos[i]) / speed[i]);
        int res = 0; double cur = 0;
        for (double time : m.values()) {
            if (time > cur) {
                cur = time;
                res++;
            }
        }
        return res;
}