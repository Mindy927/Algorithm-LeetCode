/*
Given the coordinates of four points in 2D space, return whether the four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:
Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True
Note:

All the input integers are in the range [-10000, 10000].
A valid square has four equal sides with positive length and four equal angles (90-degree angles).
Input points have no order.

Author: Mindy927*/

class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Set<Integer> set = new HashSet<>(Arrays.asList(dist(p1,p2), dist(p1,p3), dist(p1,p4), dist(p2,p3), dist(p2,p4), dist(p3,p4)));
        
        //two distances, 1 for edge, 1 for diagonal
        return !set.contains(0) && set.size()==2;
    }

    public int dist(int[] a, int[] b){
        return ( a[0] - b[0]) * ( a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);        
    }
}