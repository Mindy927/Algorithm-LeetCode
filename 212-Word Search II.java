/*Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example:

Input: 
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

Output: ["eat","oath"]

Author: Mindy927 */

class Solution {
    class TrieNode{
        char val;
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
        public TrieNode(){}
        public TrieNode(char val){
            this.val = val;
        }
    }
    
    TrieNode head;
    public List<String> findWords(char[][] board, String[] words) {
        head = new TrieNode();
        Set<String> res = new HashSet<>();
        int m = board.length, n = board[0].length;
        //inisialize a singel visited array since dfs search will clear path, no need inisialize visited array for all possible start
        boolean[][] visited = new boolean[m][n]; 
        
        for (String word:words) addToDict(word);
        
        for(int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                search(board, visited, i,j, head,"", res);
            }
        }
        return new ArrayList<>(res);
    }
    
    public void addToDict(String word){
        TrieNode cur = head;
        for (int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode(c);
            cur = cur.children[c - 'a'];
        }
        cur.isEnd = true;
    }
    
    static final int[] DIRS = new int[]{1, 0, -1, 0, 1};
    //add to res whhen we found a word ends at board[i][j]
    public void search(char[][] board, boolean[][] visited, int i, int j, TrieNode cur,String temp, Set<String> res){
        if (cur.isEnd) res.add(temp); //add to res first when reaching end of word before checking next char board[i][j]
        
        if (i<0 || j< 0 || i>= board.length || j >= board[0].length ) return;
        if (visited[i][j] || cur.children[board[i][j] - 'a']==null) return;
        
        visited[i][j] = true;
        for (int d=0; d<4; d++){
            int x = i + DIRS[d];
            int y = j + DIRS[d+1];
            search(board, visited, x, y, cur.children[board[i][j]-'a'], temp + board[i][j], res);
        }
        visited[i][j] = false;
        
    }
}