/*You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

Example 1:

Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input:
  s = "wordgoodstudentgoodword",
  words = ["word","student"]
Output: []

Author: Mindy927*/

/* 
Hash map + two pointers
i iterates string s, build unvisited = new HashMap<>(map) for each i
and try to clear unvisited words, add to result when unvisited is empty
*/


//Method 1: Two map, copy to unvisited map for each possible index i, verify and add to result
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s.length() == 0 || words.length==0 ) return result;
        int len = words[0].length();
        int n = words.length;
        
        Map<String, Integer> map = new HashMap<>();
        for (String word:words) map.put(word, map.containsKey(word)? map.get(word)+1:1);
        
        for (int i=0; i<s.length() - n * len + 1; i++){ //all possible indices
            Map<String, Integer> unvisited = new HashMap<>(map);
            int match = 0; //number of words matched
            for (int j=0; j<words.length; j++){
                String word = s.substring(i+j*len, i+(j+1)*len);
                if (!unvisited.containsKey(word) || unvisited.get(word) < 1){
                    break;
                }
                unvisited.put(word, unvisited.get(word)-1); //one more word matched
                match++;
            }
            if (match == n) result.add(i);
        }
        
        return result;
    }
}


//Method 2: Sliding window
class Solution {
    //[## idea ##]
    // Sliding Window
    
    // ask interviewer if words is empty, should I return empty list
    // words list contains duplicated word?
    
    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> res = new LinkedList<>();
        //input validation
        if (L.length == 0 || S.length() < L.length * L[0].length())   return res;
        
        int N = S.length(), M = L.length, K = L[0].length();
        Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();
        
        for (String s : L) {
            map.put(s, map.getOrDefault(s,0)+1);
        }
        String str = null, tmp = null;
        //traverse all the posibilities
        for (int i = 0; i < K; i++) {
            int count = 0;  // remark: reset count 
            //l is the start point, r+k is the end point
            for (int l = i, r = i; r + K <= N; r += K) {
                str = S.substring(r, r + K);
                if (map.containsKey(str)) {
                    curMap.put(str, curMap.getOrDefault(str,0) + 1);
                    
                    if(curMap.get(str) <= map.get(str)) count++;
                    //move l
                    while (curMap.get(str) > map.get(str)) {
                        tmp = S.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;
                        if (curMap.get(tmp) < map.get(tmp)) count--;
                    }
                    
                    if (count == M) {
                        res.add(l);
                        tmp = S.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;
                        count--;
                    }
                }else {
                    curMap.clear();
                    count = 0;
                    l = r + K;
                }
            }
            curMap.clear();
        }
        return res;
    }
}