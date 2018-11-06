/*
We are given a list of (axis-aligned) rectangles.  Each rectangle[i] = [x1, y1, x2, y2] , where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the coordinates of the top-right corner of the ith rectangle.

Find the total area covered by all rectangles in the plane.  Since the answer may be too large, return it modulo 10^9 + 7.

Example 1:

Input: [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
Output: 6
Explanation: As illustrated in the picture.
Example 2:

Input: [[0,0,1000000000,1000000000]]
Output: 49
Explanation: The answer is 10^18 modulo (10^9 + 7), which is (10^9)^2 = (-7)^2 = 49.
Note:

1 <= rectangles.length <= 200
rectanges[i].length = 4
0 <= rectangles[i][j] <= 10^9
The total area covered by all rectangles will never exceed 2^63 - 1 and thus will fit in a 64-bit signed integer

Author: Mindy927*/

class Solution {
    //consider intervals along y-axis for each x
    public int rectangleArea(int[][] rectangles) {
        List<int[]> points = new ArrayList<>();
        int M = 1000000007;
        
        //add all four points of a rec tangel, 1 for start, -1 for end
        for (int[] rec:rectangles){
            points.add(new int[]{rec[0], rec[1], 1});  //[x1, y1]
            points.add(new int[]{rec[0], rec[3], -1}); //[x1, y2]
            points.add(new int[]{rec[2], rec[1], -1});  //[x2, y1]
            points.add(new int[]{rec[2], rec[3], 1}); //[x2, y2]
        }
        
        Collections.sort(points, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0]!=b[0]? a[0]-b[0]:b[1]-a[1];     
            }
        });
        
        TreeMap<Integer, Integer> map = new TreeMap<>(); //current critical point for y-axis:1/-1 indicating start/end
        int preX = -1;
        int preY = -1;
        long res = 0;
        for (int i=0; i<points.size(); i++){
            int[] p = points.get(i);
            map.put(p[1], map.getOrDefault(p[1], 0)+ p[2]);
            if ( i== points.size()-1 ||  points.get(i+1)[0] > points.get(i)[0]){//update result and current y points when reach end or x-axis changes
                if (preX > -1) {
                    res += (long) preY * (long)(p[0] - preX);
                    res %= M;
                }
                
                preY = calcY(map);
                preX = p[0];
            }
        }
        
        return (int)res;
    }
    
    private int calcY(TreeMap<Integer, Integer> map) {
        int result = 0, pre = -1, count = 0;
        for (int y : map.keySet()) {
            if (pre >= 0 && count > 0) { //add gap between neighbors when cnt>0
                result += y - pre; 
            }
            count += map.get(y);
            pre = y;
        }
        return result;
    }
}
