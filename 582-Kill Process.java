/*

Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.

We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.

Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.

Example 1:
Input: 
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5
Output: [5,10]
Explanation: 
           3
         /   \
        1     5
             /
            10
Kill 5 will also kill 10.

Author: Mindy927*/

class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>(); // ppid : pid (a list of direct children)
        
        for (int i=0; i<ppid.size(); i++){
            if (ppid.get(i)==0) continue;
            List temp = map.containsKey(ppid.get(i))? map.get(ppid.get(i)): new ArrayList<>();
            temp.add(pid.get(i));
            map.put(ppid.get(i), temp);
        }
        
        q.offer(kill);
            
        while (!q.isEmpty()){
            int cur = q.poll();
            result.add(cur); //add its child to be killed
            if (map.containsKey(cur)){
                for (Integer child:map.get(cur)) q.offer(child);
            }
        }
        
        return result;
    }
}