/*
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.

Author: Mindy927 */

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        List<String> result = new ArrayList<>();
        
        for (String word:words){
            map.put(word, map.containsKey(word)? map.get(word)+1:1);
        }
        
        PriorityQueue<String> pq = new PriorityQueue<String>(words.length, new Comparator<String>(){
            public int compare(String a, String b){
                if (map.get(b)!= map.get(a))  return map.get(b) - map.get(a); //high frequency
                else return a.compareTo(b); // low alphabetical order
            }
        });
            
        for (String word:map.keySet()) {
            pq.offer(word);
        }
        
         while(k--> 0 ){
             result.add(pq.poll());
         }
        
        return result;
    }
}