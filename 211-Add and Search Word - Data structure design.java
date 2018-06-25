/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true

Authour: Mindy927 */


class TrieNode{
    char val;
    boolean isEnd;
    TrieNode[] children = new TrieNode[26];
    public TrieNode(){}
}

public class WordDictionary {
    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
        root.val = ' ';
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null){
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c-'a'];
        }
        cur.isEnd = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }
    
    //Returns true if word.substring(i,word.length()) matches from cur node
    private boolean match(char[] chars, int i, TrieNode cur){
        if (i==chars.length) return cur.isEnd;
        if (chars[i] != '.'){
            return (cur.children[chars[i] - 'a']!=null) && match (chars, i+1, cur.children[chars[i] - 'a']);
        }else {
            for (TrieNode node:cur.children){
                if (node!=null && match(chars,i+1, node)) return true;
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */