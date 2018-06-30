/*
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

Example:

Input: s = "++++"
Output: true 
Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Author: Mindy927 */



//BackTracking
class Solution {
    public boolean canWin(String s) {
        HashMap<String, Boolean> map = new HashMap<>(); //Memorize result for each string encountered 
        
        if (map.containsKey(s)) return map.get(s);
        
        for (int i=0; i<s.length()-1; i++){
            if (s.startsWith("++",i)){
                String next = s.substring(0,i)+ "--" + s.substring(i+2,s.length());
                if (!canWin(next)) {
                    map.put(next, true);
                    return true;
                }
            }
            
        }
        
        map.put(s, false);
        return false;
    }
}