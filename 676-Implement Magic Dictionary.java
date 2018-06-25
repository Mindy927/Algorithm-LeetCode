/*Implement a magic directory with buildDict, and search methods.

For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.

Example 1:
Input: buildDict(["hello", "leetcode"]), Output: Null
Input: search("hello"), Output: False
Input: search("hhllo"), Output: True
Input: search("hell"), Output: False
Input: search("leetcoded"), Output: False

Author: Mindy927*/


class MagicDictionary {
    class TrieNode{
        boolean isEnd;
        TrieNode[] children = new TrieNode[26];
        public TrieNode(){}
    }
    
    /** Initialize your data structure here. */
    TrieNode root;
    public MagicDictionary() {
        root = new TrieNode();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
      for (String str:dict){
          TrieNode cur = root;
          for (int i=0; i<str.length(); i++){
              char c = str.charAt(i);
              if (cur.children[c-'a'] ==null) cur.children[c-'a'] = new TrieNode();
              cur = cur.children[c-'a'];
          }
          cur.isEnd = true;
      }    
    }
    
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        TrieNode cur = root;
        boolean modified = false;
        char[] chars = word.toCharArray();
        
        for (int i=0; i<word.length(); i++){
            char prev = chars[i];
            for (char c ='a'; c<='z'; c++){
                if (c == prev) continue;
                chars[i] = c;
                String temp = String.valueOf(chars);
                if (searchHelper(temp)) return true;
            }
            chars[i] = prev;
        }
        return false;
    }
    
    //return true word matches
    public boolean searchHelper(String word){
        TrieNode cur = root;
        for (int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if (cur.children[c - 'a']==null) return false;
            cur = cur.children[c - 'a'];
        }
        return cur.isEnd;
    }
     
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */