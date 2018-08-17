/*
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

Author: Mindy927 */

class Solution {
    class TrieNode{
        int index = -1; //index of word ends at current trieNode
        List<Integer> list = new ArrayList<>();
        TrieNode[] children = new TrieNode[26];
        public TrieNode(){}
    }
    
    TrieNode head;
    public List<List<Integer>> palindromePairs(String[] words) {
        head = new TrieNode();
        List<List<Integer>> result = new ArrayList<>();
        for (int i=0; i<words.length; i++){
            addToDict(words[i], i);
        }
        
        for (int i=0; i<words.length; i++){
            search(words[i], i, result);
        }
        return result;
    }
    
    public void addToDict(String word, int idx){ 
        TrieNode cur = head;
        for (int i=word.length()-1; i>=0; i--){//add word reversely to dict
            char c = word.charAt(i);
            if (isPalindrome(word.substring(0,i+1))) cur.list.add(idx);  //[0,i] is palindrome
            if (cur.children[ c - 'a'] == null) cur.children[ c -'a' ] = new TrieNode();
            cur = cur.children[ c - 'a' ];
        }
        cur.index = idx;
        cur.list.add(idx); //dont forget to node ends at index at curent node
    }
    
    public void search(String word, int idx, List<List<Integer>> result){
        TrieNode cur = head;
        for (int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if (isPalindrome(word.substring(i)) && cur.index!= idx && cur.index!=-1) //abss & ba
                result.add(Arrays.asList(idx, cur.index)); 
            if (cur.children[ c - 'a'] == null) return;
            cur = cur.children[ c - 'a'];
        }
        
        for(int k:cur.list){ //ab & llab
            if (idx==k) continue;
            result.add(Arrays.asList(idx, k));
        }
    }
    
    public boolean isPalindrome(String str){
        int i = 0, j = str.length()-1;
        while (i < j){
            if (str.charAt(i) != str.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }   
}