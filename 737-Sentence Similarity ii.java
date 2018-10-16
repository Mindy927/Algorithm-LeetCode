/*
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].

Author: Mindy927*/

class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        Map<String, String> map = new HashMap<>(); //string, parent string
        //initialize parent as itself, similiar to union find
        for (String[] pair:pairs){
            map.put(pair[0], pair[0]);
            map.put(pair[1], pair[1]);
        }
        
        //union
        for (String[] pair:pairs) union(map, pair[0], pair[1]);
        
        //verify
        if (words1.length != words2.length) return false;
        for(int i=0; i<words1.length; i++){
            if (words1[i].equals(words2[i])) continue;
            if (!map.containsKey(words1[i]) || !map.containsKey(words2[i])) return false; //doesnt have similir word
            String root1 = find(map, words1[i]);
            String root2 = find(map, words2[i]);
            if (!root1.equals(root2)) return false; //if root is not same, means they are not in same subset
        }
        
        return true;
    }
    
    public String find(Map<String, String> map, String x){
        while (map.get(x) != x){
            x = map.get(x);
        }
        return x;
    }
    
    public void union(Map<String, String> map, String x, String y){
        String rootX = find(map, x);
        String rootY = find(map, y);
        if (rootX == rootY) return;
        map.put(rootX, rootY);
    }
}