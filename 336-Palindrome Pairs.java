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
        int index = -1;
        TrieNode[] children = new TrieNode[26];
        //List of indices of words whose rest of substring(till the end node) is palindrome. 
        //Example: words[9] = sscba, 9 is stored at TrieNode 's', since 'ss' is palindrome. 
        List<Integer> list = new ArrayList<>(); 
        public TrieNode() {}
    }
    
    TrieNode root;
    public List<List<Integer>> palindromePairs(String[] words) {
        root = new TrieNode();
        List<List<Integer>> result = new ArrayList<>();
        for (int i=0; i<words.length; i++){
            addToDict(words[i],i);
        }
        for (int i=0; i<words.length; i++){
            search(words, i, result);
        }
        
        return result;
    }
    
    public void search(String[] words, int i, List<List<Integer>> result){
           TrieNode cur = root;
           for (int j=0; j<words[i].length(); j++){
                char c = words[i].charAt(j);
                if (isPalindrome(words[i],j,words[i].length()-1) && cur.index!=i && cur.index !=-1){ 
                    // words[i] = abcc  cur=a->b->End  ==>abcc ba
                    result.add(Arrays.asList(i, cur.index));
                }
                if (cur.children[c-'a']==null) return;
                cur = cur.children[c-'a'];
            }
            
           //words[i] = ab  cur=a->b->cc (reverse order)  ==>ab ccba
            for (int k:cur.list){
                if (i!=k) result.add(Arrays.asList(i, k));
            }
    }
    
    public void addToDict(String word, int k){
        TrieNode cur = root;
        for (int i=word.length()-1; i>=0; i--){ //Store words reversely 
            char c = word.charAt(i);
            if (isPalindrome(word, 0, i)) cur.list.add(k);
            if (cur.children[c - 'a']==null) cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }
        cur.list.add(k);
        cur.index = k;
    }
    
    public boolean isPalindrome(String word, int i, int j){
        while (i < j){
            if (word.charAt(i++)!=word.charAt(j--)) return false;
        }
        return true;
    }
}