/*In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

Example 1:
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"

Author: Mindy927 */



class Solution {
    class TrieNode{
        boolean isEnd;
        TrieNode[] children = new TrieNode[26];
        
        public TrieNode(){}
    }
    
    TrieNode root;
    public String replaceWords(List<String> dict, String sentence) { 
        String[] strs = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        root = new TrieNode();
        
        for (String word:dict){
            addToTrie(word);
        }
        
        for (int i=0; i<strs.length; i++){
            String rep = searchReplacement(strs[i]);
            if (i==0) sb.append(rep);
            else sb.append(" " + rep);
        }
        
        return sb.toString();
    }
    
    public void addToTrie(String word){
        TrieNode cur = root;
        for (int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if (cur.children[c -'a'] == null) cur.children[c -'a'] = new TrieNode();
            cur = cur.children[c -'a'];
        }
        cur.isEnd = true;
    }
    
    public String searchReplacement(String word){
        TrieNode cur = root;
        for (int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null) return word;
            else if (cur.children[c-'a'].isEnd) return word.substring(0,i+1); //i+1 since cur.children is end not curr
            else cur = cur.children[c-'a'];
        }
        return word;
    }
}