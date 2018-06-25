/*Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.

Authour: Mindy927 */

class Solution {
    class TrieNode{
        TrieNode[] children; 
        public TrieNode(){
            children = new TrieNode[2];
        }
    }
    
    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();
        int max = 0;
        
        for (int num:nums) addToTrie(num,root);
        
        for(int num: nums) {
            TrieNode curNode = root;
            int curSum = 0;
            for(int i = 31; i >= 0; i --) {
                int curBit = (num >>> i) & 1;
                if(curNode.children[curBit ^ 1] != null) {//Found number has different bit at ith pos
                    curSum += (1 << i);
                    curNode = curNode.children[curBit ^ 1];
                }else {
                    curNode = curNode.children[curBit];
                }
            }
            max = Math.max(curSum, max);
        }
        
        return max;
    }
    
    public void addToTrie(int num, TrieNode root){
        TrieNode cur = root;
        for (int i = 31; i>=0; i--){
            int curBit = (num >>> i) & 1;
            if (cur.children[curBit] == null) cur.children[curBit] = new TrieNode();
            cur = cur.children[curBit];
        }
    }   
}

