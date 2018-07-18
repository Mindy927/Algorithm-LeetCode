/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

Author: Mindy927 */

//bfs with used set(or remove visited string from dict set)
//bfs with used set
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        for (String word:wordList) dict.add(word);
        
        if (!dict.contains(endWord)) return 0; //corner case
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int level = 1;
        
        while (!q.isEmpty()){
            level++;
            int size = q.size();
            for(int i=0; i<size; i++){
                char[] chars = q.poll().toCharArray();
                for (int j=0; j<chars.length; j++){ //replace s.charAt(j) with c
                    for (char c = 'a'; c<='z'; c++){
                        char prev = chars[j];
                        if (chars[j] == c) continue;
                        chars[j] = c;
                        String temp = String.valueOf(chars);
                        if (temp.equals(endWord)) return level;
                        if (dict.contains(temp)) {
                            q.offer(temp);
                            dict.remove(temp);
                        }
                        chars[j] = prev;  //Dont forget to revert!
                    } 
                }
            }
        }
        
        return 0;
    }
}