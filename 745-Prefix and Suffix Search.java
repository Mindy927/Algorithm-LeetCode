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

/*
wrap each word with all possible prefix + "{" + reversedWord
example: apple => {eppla, a{eppla, ap{eppla, app{eppla, appl{elppa ..
When search prefix "a", suffix "e" => search "a{e"

*/
class WordFilter {
    class TrieNode{
        int index = -1;
        TrieNode[] children = new TrieNode[27]; //27th char for "{"
        public TrieNode(){}        
    }
    
    TrieNode head;
    public WordFilter(String[] words) {
        head = new TrieNode(){};
        for (int i=0; i<words.length; i++){
            StringBuilder sb = new StringBuilder(words[i]);
            String reverse = sb.reverse().toString();
            for (int j=0; j<=words[i].length(); j++){ //wrap each word with all possible suffix(word.substring(j))
                String temp = words[i].substring(0,j) + "{" +  reverse; 
                addToDict(temp, i);
            }
        }    
    }
    
    public void addToDict(String word, int index){
        TrieNode cur = head;
        for (int i=0; i<word.length(); i++){
            cur.index = index;
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }
        cur.index = index; //overwrite previous index
    }

    public int f(String prefix, String suffix) {
             StringBuilder sb = new StringBuilder(suffix); //reverse suffix before searching
            String reverse = sb.reverse().toString();
            return search(prefix + "{" + reverse);
    }
    
    public int search(String word){
        TrieNode cur = head;
        for (int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if (cur.children[ c - 'a'] == null) return -1;
            cur = cur.children[c - 'a'];
        }
        return cur.index;
    }
    
}


/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */