/*
Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)

Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).

When you get an instruction "A", your car does the following: position += speed, speed *= 2.

When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)

For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.

Now for some target position, say the length of the shortest sequence of instructions to get there.

Example 1:
Input: 
target = 3
Output: 2
Explanation: 
The shortest instruction sequence is "AA".
Your position goes from 0->1->3.
Example 2:
Input: 
target = 6
Output: 5
Explanation: 
The shortest instruction sequence is "AAARA".
Your position goes from 0->1->3->7->7->6.
 

Note:

1 <= target <= 10000.

Author: Mindy927*/

class Solution {
    class State{
        int speed;
        int pos;
        public State(int x, int y){
            speed = x;
            pos = y;
        } 
    }
    
    //BFS of states(speed, pos)
    public int racecar(int target) {
        Set<String> visited = new HashSet<>();
        Queue<State> q = new LinkedList<>();
        q.offer(new State(1, 0));
        visited.add( 1 + "-" + 0);
        
        int level = 0;
        while (!q.isEmpty()){
            int size = q.size();     
            for(int s=0; s<size; s++){
                State cur = q.poll();
                if (cur.pos == target) return level;
                //'A'
                State next1 = new State(cur.speed*2, cur.pos+cur.speed);
                String temp1 =  next1.speed + "-" + next1.pos;
                if (Math.abs(next1.pos - target) < target && !visited.contains(temp1)){ //stop searching when it pos is 2*target since speed increase exponentially
                    q.offer(next1);
                    visited.add(temp1);
                }
                //'R'
                State next2 = new State( (cur.speed>0? -1:1), cur.pos);
                String temp2 =  next2.speed + "-" + next2.pos;
                if (Math.abs(next2.pos - target) < target && !visited.contains(temp2)){
                    q.offer(next2);
                    visited.add(temp2);
                }
            }
            level++;
        }
        
        return -1;
        
    }
}