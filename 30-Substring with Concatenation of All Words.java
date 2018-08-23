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