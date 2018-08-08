/*

You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

Example 1:
Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".
Example 2:
Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation:
We can turn the last wheel in reverse to move from "0000" -> "0009".
Example 3:
Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation:
We can't reach the target without getting stuck.
Example 4:
Input: deadends = ["0000"], target = "8888"
Output: -1
Note:
The length of deadends will be in the range [1, 500].
target will not be in the list deadends.
Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000' to '9999'.

Author:Mindy927*/

class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> set = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        char[] map = new char[]{'9','0','1','2','3','4','5','6','7','8','9','0'};
        for (String str:deadends) set.add(str);
        
        if (set.contains("0000") || set.contains(target)) return -1;
        q.offer("0000");
        visited.add("0000");
        
        int level = 0;
        while (!q.isEmpty()){
            level++;
            int size = q.size();
            for (int s=0; s<size; s++){
                char[] prev = q.poll().toCharArray();
                for (int i=0; i<4; i++){ 
                    int temp = prev[i] - '0'; //digit to be modified
                    prev[i] = map[temp]; //-1
                    String next1 = String.valueOf(prev);
                    if (next1.equals(target)) return level;
                    if (!set.contains(next1) && !visited.contains(next1)) {
                        q.offer(next1); visited.add(next1);
                    }
                    prev[i] = map[temp+2]; //+1
                    String next2 = String.valueOf(prev);
                    if (next2.equals(target)) return level;
                    if (!set.contains(next2) && !visited.contains(next2)) {
                        q.offer(next2); visited.add(next2);
                    }                    
                    prev[i] = map[temp+1]; //revert
                }
            }
        }
        
        return -1;
    }
}