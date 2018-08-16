/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
] 

Output: "" 

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

Author:Mindy927*/

class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>(); //char: all chars after current char
        Map<Character, Integer> indegree = new HashMap<>();//indegree for each char
        
        //add indegree 0 for all chars
        for (String word:words){
            for (char c:word.toCharArray()){
                indegree.put(c, 0);
            }
        }
        
        //for each adjacent pair, find first char that differ and add to pre-req map, break
        for (int i=0; i<words.length-1; i++){
            String str1 = words[i];
            String str2 = words[i+1];
            int j=0;
            for (;j<Math.min(str1.length(), str2.length()); j++){
                char c1 = str1.charAt(j);
                char c2 = str2.charAt(j);
                if (c1 == c2) continue;    
                if (!map.containsKey(c1)) map.put(c1, new HashSet<>());
                if (map.containsKey(c2) && map.get(c2).contains(c1)) return ""; //c2 < c1, loop
                //add indegree only when encounter c1,c2 pair at first time
                if (!map.get(c1).contains(c2)) {// c1 is new pre-req for c2
                    indegree.put(c2, indegree.get(c2)+1); 
                    map.get(c1).add(c2); 
                }
                break; 
            }
        }
        
        //add chars with 0 indegree to queue
        Queue<Character> q = new LinkedList<>();
        for(char c:indegree.keySet()){
            if (indegree.get(c) == 0) q.offer(c);
        }
        
        //bfs
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()){
            char cur = q.poll();
            sb.append(cur);
            indegree.remove(cur); //remove from indegree map when current char has been used
            if (!map.containsKey(cur)) continue;
            for (char next:map.get(cur)){
                if (indegree.get(next) > 0){
                    indegree.put(next, indegree.get(next)-1);
                    if (indegree.get(next)==0) {
                        q.offer(next);
                    }
                }
            }
        }
        
        
        return indegree.isEmpty()? sb.toString():""; //make sure all chars has been used before return
    }
}