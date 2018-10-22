/*
Alice has a hand of cards, given as an array of integers.

Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.

Return true if and only if she can.

 

Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
Example 2:

Input: hand = [1,2,3,4,5], W = 4
Output: false
Explanation: Alice's hand can't be rearranged into groups of 4.

Author: Mindy927*/


/*
-- greedily get W smallest consecutive cards, clear smallest number by subtracting map.get(smallest) for W consecutive cards
-- TreeMap of <sored cards:cnt>
-- O(MlgM), M:# unique numbers
*/
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        TreeMap<Integer, Integer> map = new TreeMap<>(); //card:cnt
        for (int num:hand) map.put(num, map.getOrDefault(num, 0)+1);
        
        for (int num:map.keySet()){
            int cnt = map.get(num); //minus cnt for all W consecutive numbers, clear at least one entry
            if (cnt == 0) continue;
            for (int i=0; i<W; i++){
                if (!map.containsKey(num+i) || map.get(num + i) < cnt) return false;
                map.put(num+i, map.get(num+i)-cnt);
            }
        }
        
        return true;
        
    }
}