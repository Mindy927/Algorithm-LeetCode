/*
A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.

Author: Mindy927 */


/*
hashMap<char, lastIndex>
2 pass
1. curEndt = last index of first char
2. nterate through and update curEnd
3. add parts to result when curEnd = i
  — Reset cnt =0 & curEnd to last index of next char
 */

class Solution {
    public List<Integer> partitionLabels(String S) {
        int n = S.length();
        HashMap<Character, Integer> map = new HashMap<>(); // char:lastIndex
        List<Integer> result = new ArrayList<>();
        if (n==0) return result;
        
        for (int i=0; i<n; i++) map.put(S.charAt(i),i);
        
        int cnt = 0, curEnd = map.get(S.charAt(0));
        for (int i=0; i<n; i++){
            cnt++;
            if (curEnd == i) {
                result.add(cnt);
                cnt = 0;
                curEnd = i+1<n? map.get(S.charAt(i+1)):n-1; //reset curEnd to next char
            }
            curEnd = Math.max(curEnd, map.get(S.charAt(i)));
        }
        
        return result;
    }
}