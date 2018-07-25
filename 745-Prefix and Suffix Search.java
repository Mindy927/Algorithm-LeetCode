/*
Given many words, words[i] has weight i.

Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix). It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.

Examples:
Input:
WordFilter(["apple"])
WordFilter.f("a", "e") // returns 0
WordFilter.f("b", "") // returns -1
Note:
words has length in range [1, 15000].
For each test case, up to words.length queries WordFilter.f may be made.
words[i] has length in range [1, 10].
prefix, suffix have lengths in range [0, 10].
words[i] and prefix, suffix queries consist of lowercase letters only.
Author: Mindy927*/


class WordFilter {
    class TrieNode{
        TrieNode[] children = new TrieNode[27]; //including '{'
        char val;
        int weight = -1;
        public TrieNode(){};
        public TrieNode(char c){
            this.val = c;
        }
    }
    
    TrieNode head;
    public WordFilter(String[] words) {
            head = new TrieNode();
            for (int i=0; i<words.length; i++){
                String word = words[i];
                for (int j=0; j<=word.length(); j++){  //wrap each word with all possible suffix(word.substring(j))
                    String temp = word.substring(j) + "{" + word;
                    insert(temp, i);
                }
            }
    }
    
    public void insert(String word, int w){
        TrieNode cur = head;
        for (int i=0; i<word.length(); i++){
            if (cur.weight < w) cur.weight = w;
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode(c);
            cur = cur.children[c - 'a'];
        }
        if (cur.weight < w) cur.weight = w;
    }
    
    public int f(String prefix, String suffix) {
        String temp = suffix + '{' + prefix;
        return search(temp);
    }
    
    public int search(String word){
        TrieNode cur = head;
        for (int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null) return -1;
            cur = cur.children[c - 'a'];
        }
        return cur.weight;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */