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

//two-end bfs
/*
- beginSet start from beginWord, endSet start from endWord
- visited store all strings have been visited so far
- use temp set to store words to be searched in next round

*/

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //for the bi-directional graph, begin set keeps elements in begin set at biggest depth(elements added in last round)
        Set<String> beginSet = new HashSet<>(); 
        Set<String> endSet = new HashSet<>();
        Set<String> dict = new HashSet<>();
        Set<String> visited = new HashSet<>();
        
        for (String word:wordList) dict.add(word);
        if (!dict.contains(endWord))  return 0;
            
        beginSet.add(beginWord);
        endSet.add(endWord);
        
        int dist = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()){
            dist++;
            //make sure begin set is smaller
            if (beginSet.size() > endSet.size()){
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            
            //for each string in beginSet, modify 1 char and check whether it can reach endSet
            Set<String> temp = new HashSet<>(); //string to be searched in next round
            for(String str:beginSet){
                char[] chars = str.toCharArray();
                for (int i=0; i<chars.length; i++){
                    char prev = chars[i];
                    for (char c='a'; c<='z'; c++){
                        chars[i] = c;
                        String next = String.valueOf(chars);
                        if (endSet.contains(next)) return dist;
                        if (dict.contains(next) && !visited.contains(next)) {
                            temp.add(next);
                            visited.add(next);
                        }
                    }
                    chars[i] = prev; //convert ith char back
                }
            }
            beginSet = temp;
        }
        
        return 0;
    }
}



//bfs with used set(or remove visited string from dict set)
//bfs with used set
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        int level = 0;
        
        for (String word:wordList){
            dict.add(word);
        }
        if (!dict.contains(endWord)) return 0;
        
        q.offer(beginWord);
        visited.add(beginWord);
        while(!q.isEmpty()){
            level++;
            int size = q.size();
            for(int s=0; s<size; s++){
                String word = q.poll();
                char[] chars = word.toCharArray();
                for (int i=0; i<word.length(); i++){
                    char prev = chars[i];
                    for (char c = 'a'; c < 'z'; c++){
                        chars[i] = c;
                        String temp = String.valueOf(chars);
                        if (temp.equals(endWord)) return level+1;
                        if (!visited.contains(temp) && dict.contains(temp)){
                            visited.add(temp);
                            q.offer(temp);
                        }
                        chars[i] = prev;
                    }
                }
            }
        }
        
        return 0;
        
    }
}