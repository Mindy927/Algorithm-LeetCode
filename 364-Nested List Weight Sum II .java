/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 8 
Explanation: Four 1's at depth 1, one 2 at depth 2.
Example 2:

Input: [1,[4,[6]]]
Output: 17 
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 

Author: Mindy927*/

class Solution {
    /* BFS
    Instead of multiplying by depth, add integers multiple times (by going level by level and adding the unweighted sum to the weighted sum after each level)
    */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int res = 0, cur = 0;  
        while (!nestedList.isEmpty()){
            List<NestedInteger> temp = new ArrayList<>();
            for( NestedInteger k:nestedList ){
                if ( k.isInteger() ) cur += k.getInteger();
                else {
                    for (NestedInteger nei:k.getList()){
                        temp.add(nei);
                    }
                }
            }
            nestedList = temp;
            res += cur; //Key step, if a node's value is added to res, it will be added one more time in each round
        }
        
        return res;
    }
    
}