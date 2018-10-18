/*

On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Input: board = [[3,2,4],[1,5,0]]
Output: 14
Note:

board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].

Author: Mindy927*/


//bfs, whole board as a state
class Solution {   
    public int slidingPuzzle(int[][] board) {
        int[] DIRS = new int[]{-1, 1, -3, 3}; //possible move
        Set<String> visited = new HashSet<>();
        
        //initialise 
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<2; i++){
            for (int j=0; j<3; j++){
                sb.append(String.valueOf(board[i][j]));
            }
        }
        String start = sb.toString();
        String target = "123450";
        

        //bfs
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        int level = 0;
        while (!q.isEmpty()){
            level++;
            int size = q.size();
            for(int s=0; s<size; s++){
                String cur = q.poll();
                if (cur.equals(target)) return level-1;
                int pos1 = cur.indexOf("0");
                for(int d=0; d<4; d++){
                    //swap char at pos1 and pos2
                    int pos2 = pos1 + DIRS[d];
                    //skip invalid swap
                    if (pos2 < 0 || pos2 > 5 || (pos1 == 2 && pos2 == 3) || (pos1 == 3 && pos2 == 2)) { continue; } 
                    char[] chars = cur.toCharArray();
                    char temp = chars[pos1];
                    chars[pos1] = chars[pos2];
                    chars[pos2] = temp;
                    String next = String.valueOf(chars);
                    if (!visited.contains(next)){
                        visited.add(next);
                        q.offer(next);      
                    }
                }
            }
        }
        
        return -1;
        
    }
}