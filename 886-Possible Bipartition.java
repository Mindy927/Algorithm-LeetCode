/*
Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

Each person may dislike some other people, and they should not go into the same group. 

Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

Return true if and only if it is possible to split everyone into two groups in this way.

 

Example 1:

Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]
Example 2:

Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Example 3:

Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false

Author: Mindy927*/


class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> map = new HashMap<>(); //A: all people A does not like
        int[] colors = new int[N+1];
        Arrays.fill(colors, -1);
        
        for (int[] pair:dislikes){
            List<Integer> temp0 = map.containsKey(pair[0])? map.get(pair[0]):new ArrayList<>();
            temp0.add(pair[1]);
            map.put(pair[0], temp0);
            List<Integer> temp1 = map.containsKey(pair[1])? map.get(pair[1]):new ArrayList<>();
            temp1.add(pair[0]);
            map.put(pair[1], temp1);
        }
        
        for(int i=0; i<N+1; i++){
            if (colors[i] == -1 && !dfs(colors, i, 0, map)) return false;
        }
        
        return true;
    }
    
    //whether we are able to color current node with given color 
    public boolean dfs(int[] colors, int i, int color, Map<Integer, List<Integer>> map){
        if (colors[i]!=-1) return colors[i] == color;
        
        colors[i] = color;
        if (!map.containsKey(i)) return true;
        for (int nei:map.get(i)){ //color all people i doesn't like with another color(0->1, 1->0)
            if (!dfs(colors, nei, 1 - color, map)) return false;
        }
        
        return true;
    }
     
}