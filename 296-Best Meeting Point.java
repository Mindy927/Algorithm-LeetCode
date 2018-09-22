/*
A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example:

Input: 

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6 

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance 
             of 2+2+2=6 is minimal. So return 6.

Author:Mindy927*/

class Solution {
    public int minTotalDistance(int[][] grid) {
        //get pos of 1s, take median of Xs andYs
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        int res = 0;
        
        for (int i=0; i<grid.length; i++){
            for (int j=0; j<grid[0].length; j++){
                if (grid[i][j] == 1){
                    x.add(i);
                    y.add(j);
                }
            }
        }
        int n = x.size();
        Collections.sort(x);
        Collections.sort(y);
        /x-axis of optimal solution is median of all Xs
        int midX = x.get(n/2); /
        int midY = y.get(n/2);
        
        for (int i=0; i<n; i++){
            res += Math.abs(x.get(i) - midX);
            res += Math.abs(y.get(i) - midY);
        }
        
        return res;
    }
}