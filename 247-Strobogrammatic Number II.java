/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:

Input:  n = 2
Output: ["11","69","88","96"]

Author: Mindy927*/

//corner case 1001, 00 wont be result in last round,
class Solution {
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }
    
    public List<String> helper(int cur, int max) {
        
        if (cur == 0) return new ArrayList<>(Arrays.asList(""));
        if (cur == 1) return new ArrayList<>(Arrays.asList("0","1","8"));
        
        List<String> temp = helper(cur-2, max);
        List<String> result = new ArrayList<>();
        
        for (String str:temp){
            if (cur != max) result.add("0" + str + "0"); //dont add 0 + str + 0 at last round
            result.add("1" + str + "1");
            result.add("8" + str + "8");
            result.add("6" + str + "9");
            result.add("9" + str + "6");
        }
        
        return result;
    }
}

//bfs
class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> res = new ArrayList<>();
        if ( n == 0 ) return res;
        if ( n%2 == 0) res.add("");
        if ( n%2 == 1 ) {
            res.add("0");
            res.add("1");
            res.add("8");
        }
        
        for (int i=0; i<n/2; i++){
            int size = res.size();
            for (int s=0; s<size; s++){
                String prev = res.get(0);
                res.remove(0);
                if (i!= n/2-1) res.add("0" + prev + "0"); //dont add  0 + str + 0 for last round, since number wont start from 0
                res.add( "1" + prev + "1");
                res.add( "6" + prev + "9");
                res.add( "9" + prev + "6");
                res.add( "8" + prev + "8");
            }
        }
        
        return res;
    }
}