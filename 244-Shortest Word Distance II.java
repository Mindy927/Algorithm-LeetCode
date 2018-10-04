/*
Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters. 

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

//get lists of indices for two words, two pointers
class WordDistance {
    Map<String, List<Integer>> map; //string:indices
    public WordDistance(String[] words) {
        map = new HashMap<>();
        for (int i=0; i<words.length; i++){
            if (!map.containsKey(words[i])) map.put(words[i], new ArrayList<>());
            map.get(words[i]).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        int res = Integer.MAX_VALUE;
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        //two pointers
        int p1 = 0, p2 = 0;
        while (p1 < list1.size() && p2 < list2.size()){
            int diff = Math.abs(list1.get(p1)-list2.get(p2));
            res = Math.min(diff, res);
            if (list1.get(p1) < list2.get(p2)) p1++;
            else p2++;
        }
        
        return res;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */