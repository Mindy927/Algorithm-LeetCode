/*
Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

Example 1:

Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
Example 2:

Input: "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.

Author: Mindy927*/

class Solution {
    //brute force
    public String nextClosestTime(String time) {
        String[] strs = time.split(":");
        Set<Character> set = new HashSet<>(); //set of digits in time
        for (char c:time.toCharArray()){
            if (c!= ':') set.add(c);
        }
       
        int prev = Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
        for (int i=1; i<=1440; i++){ //increase 1 minute each time, =1440 for corner case "00:00", cound be itself
            int cur = (prev + i) % 1440;
            String h = cur/60 < 10? "0"+ cur/60 : "" + cur/60; //9:1 invalid --> 09:01
            String m = cur%60 < 10? "0"+ cur%60 : "" + cur%60;          
            String temp = h + ":" + m;
            if (valid(temp, set)) return temp;
        }
        
        return "";
    }

    
    public boolean valid(String time, Set<Character> set){      
        for (int i=0; i<time.length(); i++){
            char c = time.charAt(i);
            if (c == ':') continue;
            if (!set.contains(c)) return false;
        }
        return true;
    }
}