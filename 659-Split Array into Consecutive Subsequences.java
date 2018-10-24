/*

You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.

Example 1:
Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3
3, 4, 5
Example 2:
Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3, 4, 5
3, 4, 5
Example 3:
Input: [1,2,3,4,4,5]
Output: False

Author: Mindy927 */
/*
Greedy with 2 hashmaps   O(n)/ O(n)
— hashMap 1 <num, count>
— hashMap 2 <lastElement, cnt> // last element : #of subsequences end with lastElement
Iterate arrays, two cases 
(1) current num can append to any of previous subsequence (find such subsequence use hashmap2.get(num-1)
(2) current num can be a start of new subsequence
==> otherwise return false
*/

class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>(); //num : freq
        Map<Integer, Integer> lastElement = new HashMap<>(); //last element : #of subsequences end with lastElement
        
        for (int num:nums) freq.put(num, freq.containsKey(num)? freq.get(num)+1:1);
        
        //for each num, if could append to previous sequence, append
        //otherwise, start a new sequence of length 3
        for (int num:nums){
            if (freq.get(num) == 0) continue; //num has been added to some subsequence
            //append num to any of subsequence created before
            if (lastElement.containsKey(num-1) && lastElement.get(num-1) > 0){
                lastElement.put(num-1, lastElement.get(num-1)-1); 
                lastElement.put(num, lastElement.containsKey(num)? lastElement.get(num)+1:1); //new subsequence ends with num
                freq.put(num, freq.get(num)-1);
            }
            //start new subsequence with num, ends with num+2
            else if (freq.containsKey(num+1) && freq.containsKey(num+2) && freq.get(num+1) > 0 && freq.get(num+2) >0){
                lastElement.put(num+2, lastElement.containsKey(num+2)? lastElement.get(num+2)+1:1);
                freq.put(num, freq.get(num)-1);
                freq.put(num+1,  freq.get(num+1) -1);
                freq.put(num+2,  freq.get(num+2) -1);
            }
            else return false;
        }
        return true;     
    }
}