/*
On an infinite number line (x-axis), we drop given squares in the order they are given.

The i-th square dropped (positions[i] = (left, side_length)) is a square with the left-most point being positions[i][0] and sidelength positions[i][1].

The square is dropped with the bottom edge parallel to the number line, and from a higher height than all currently landed squares. We wait for each square to stick before dropping the next.

The squares are infinitely sticky on their bottom edge, and will remain fixed to any positive length surface they touch (either the number line or another square). Squares dropped adjacent to each other will not stick together prematurely.


Return a list ans of heights. Each height ans[i] represents the current highest height of any square we have dropped, after dropping squares represented by positions[0], positions[1], ..., positions[i].

Example 1:
Input: [[1, 2], [2, 3], [6, 1]]
Output: [2, 5, 5]
Explanation:

After the first drop of positions[0] = [1, 2]:
_aa
_aa
-------
The maximum height of any square is 2.


After the second drop of positions[1] = [2, 3]:
__aaa
__aaa
__aaa
_aa__
_aa__
--------------
The maximum height of any square is 5.  
The larger square stays on top of the smaller square despite where its center
of gravity is, because squares are infinitely sticky on their bottom edge.


After the third drop of positions[1] = [6, 1]:
__aaa
__aaa
__aaa
_aa
_aa___a
--------------
The maximum height of any square is still 5.

Thus, we return an answer of [2, 5, 5].

Author: Mindy927 */


class Solution {
    class Interval{
        int start, end, height;
        public Interval(int start, int end, int height){
            this.start = start;
            this.end = end;
            this.height = height;
        }
    }
    

    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> result = new ArrayList<>();
        List<Interval> intervals = new ArrayList<>();
        
        int max = 0;
        for (int[] pos:positions){
            int start = pos[0];
            int end = pos[0] + pos[1] - 1;
            int prevH = 0;
            for (Interval i:intervals){
                if (i.end >= start && i.start <= end){ //overlap
                   prevH = Math.max(prevH, i.height);
                }
            }
            int newH = prevH + pos[1]; //updated height
            intervals.add(new Interval(start, end, newH)); //add new interval with height prevH+pos[1] to intervals
            
            max = Math.max(newH, max);
            result.add(max);
        }
        
        return result;
    }
}