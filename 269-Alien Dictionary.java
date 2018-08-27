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
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1); //-1 indicates all chars are not appeared in words
        Map<Character, Set<Character>> map = new HashMap<>(); //str: all strings with current str as their pre-req
        
        //1. building indegree, chars appeared with indegree 0, otherwise -1
        for (String word:words){
            for (int i=0; i<word.length(); i++){
                char c = word.charAt(i);
                if(indegree[c - 'a'] == -1) indegree[c - 'a'] = 0;
            }
        }
        
        //2. for each adjacent pair of words, find first char that differ, add to pre-req map, break, only update map when encouter preReq ->char relationship at first time
        for (int i=0; i<words.length-1; i++){
            String cur = words[i];
            String next = words[i+1];
            for (int j=0; j<Math.min(cur.length(), next.length()); j++){
                char preReq = cur.charAt(j);
                char c = next.charAt(j);
                if (preReq == c) continue;
                Set<Character> temp = map.containsKey(preReq)? map.get(preReq):new HashSet<>();
                if (!temp.contains(c)){ //first time of:preReq->c
                    indegree[c - 'a']++;
                    temp.add(c);
                }
                map.put(preReq, temp);
                break;
            }
        }
        
        //3. topology sort
        Queue<Character> q = new LinkedList<>();
        for (int i=0; i<26; i++){
            if (indegree[i] == 0) q.offer((char)(i+'a'));
        }
        
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()){
            char cur = q.poll();
            sb.append(cur);
            if (!map.containsKey(cur)) continue;
            for(char c:map.get(cur)){
                indegree[c - 'a']--;
                if (indegree[c - 'a'] == 0) q.offer(c);
            }
        }
        //return "" when any char with indegree not -1 or 0
        for (int i=0; i<26; i++){
            if (!(indegree[i]==0 || indegree[i]==-1)) return "";
        }
        
        return sb.toString();
    }
}