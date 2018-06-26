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
        boolean isEnd;
        TrieNode[] children = new TrieNode[26];      
        public TrieNode(){}
        
    }
    
    TrieNode root;
    public List<String> findWords(char[][] board, String[] words) {
        root = new TrieNode();
        Set<String> result = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        for (String word:words) addToDict(word);
        
        for(int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                search(board,visited,i,j, "",root, result);
            }
        }

        return new ArrayList<>(result);
    }
    
    
    public void addToDict(String word){
        TrieNode cur = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }
        cur.isEnd =true;
    }
    
    
    public void search(char[][] board, boolean[][] visited,int i, int j,String str,TrieNode cur,Set<String> result){
        
        if (cur.isEnd) result.add(str);
        
        if( i<0 || j<0 || i>=board.length || j >= board[0].length) return;
        if( visited[i][j] || cur.children[board[i][j]-'a']== null) return;
        
        visited[i][j] = true;
        search(board, visited, i+1, j, str+board[i][j], cur.children[board[i][j]- 'a'], result);
        search(board, visited, i-1, j, str+board[i][j], cur.children[board[i][j]- 'a'], result);
        search(board, visited, i, j+1, str+board[i][j], cur.children[board[i][j]- 'a'], result);
        search(board, visited, i, j-1, str+board[i][j], cur.children[board[i][j]- 'a'], result);
        visited[i][j] = false;
        
    }
}