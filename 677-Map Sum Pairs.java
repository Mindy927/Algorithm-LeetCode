
/*Implement a MapSum class with insert, and sum methods.

For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.

For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.

Example 1:
Input: insert("apple", 3), Output: Null
Input: sum("ap"), Output: 3
Input: insert("app", 2), Output: Null
Input: sum("ap"), Output: 5


Author: Mindy927*/ 



class MapSum {
    class TrieNode{
        int val;
        boolean isEnd;
        TrieNode[] children = new TrieNode[26];
        
        public TrieNode() {}
    }
    /** Initialize your data structure here. */
    TrieNode root;
    public MapSum() {
        root = new TrieNode();
    }
    
    public void insert(String key, int val) {
        TrieNode cur = root;
        for(int i=0; i<key.length(); i++){
            char c = key.charAt(i);
            if (cur.children[c - 'a']==null) cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[ c - 'a'];
        }
        cur.isEnd = true;
        cur.val = val;
    }
    
    public int sum(String prefix) {
        int[] sum = new int[1];
        TrieNode cur = root;
        for (int i=0; i<prefix.length(); i++){
            char c = prefix.charAt(i);
            if (cur.children[ c - 'a'] == null) return 0;
            else cur = cur.children[ c - 'a'];
        }
        search(cur, sum);
        return sum[0];
    }
    
    public void search(TrieNode cur, int[] sum){
        if (cur.isEnd) {
            sum[0] += cur.val;
        }
        //Cannot use else here since a node can either be an end for some word or has children to form other words
        for (TrieNode child:cur.children){
                if (child!=null) search(child, sum);

        } 
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */