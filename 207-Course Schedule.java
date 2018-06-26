/*There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.

Author: Mindy927*/


class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //pre is an array of LinkedList
        //pre[i] is an LinkedList of courses with ith course being their prerequisites
        LinkedList<Integer>[] pre = new LinkedList[numCourses];
        int[] degree = new int[numCourses];
        Queue<Integer> q= new LinkedList<>();
        
        for (int i=0; i<numCourses; i++){
            pre[i] = new LinkedList<>();
        }
        
        for (int i=0; i<prerequisites.length; i++){
            int course = prerequisites[i][0];
            int preReq = prerequisites[i][1];
            
            pre[preReq].add(course);
            degree[course]++;
        }
        
        //Add courses without prerequisites to queue
        for (int i=0; i<numCourses; i++){
            if (degree[i] == 0) q.offer(i);
        }
        
        //BFS
        while(!q.isEmpty()){
            int size = q.size();
            for (int i=0; i<size; i++){
                int cur = q.poll();
                for (Integer k:pre[cur]){ //cur course is prerequisite for course k
                    degree[k]--;
                    if (degree[k] == 0) q.offer(k);
                }
            }
        }
        
        for (int k:degree){
            if (k!=0) return false;
        }
        
        return true;
    }
}