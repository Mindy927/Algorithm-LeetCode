/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .


Author: Mindy927*/



class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
               //pre is an array of LinkedList
        //pre[i] is an LinkedList of courses with ith course being their prerequisites
        LinkedList<Integer>[] pre = new LinkedList[numCourses];
        int[] degree = new int[numCourses];
        Queue<Integer> q= new LinkedList<>();
        int[] result = new int[numCourses];
        int index = 0;
        
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
                result[index++] = cur;
                for (Integer k:pre[cur]){ //cur course is prerequisite for course k
                    degree[k]--;
                    if (degree[k] == 0) q.offer(k);
                }
            }
        }
        
        return (index == numCourses) ? result : new int[0];
    }
}