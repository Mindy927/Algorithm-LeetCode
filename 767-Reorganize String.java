/*

Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""

Author: Mindy927 */

class Solution {
    public String reorganizeString(String S) {
        if (S.length()==0) return S;
        Map<Character, Integer> map = new HashMap<>();
        for (int i=0; i<S.length(); i++){
            char c = S.charAt(i);
            map.put(c, map.containsKey(c)? map.get(c)+1:1);
        }
        
        PriorityQueue<Character> pq = new PriorityQueue<>((a,b) -> (map.get(b) - map.get(a)));
        
        for (Character c:map.keySet()){
            pq.offer(c);
        }
        
        int maxFreq = map.get(pq.peek());
        if (S.length()-maxFreq < maxFreq-1 ) return "";
            
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()){ //template for slot of size k ,smiliar as #621 task scheduler
            Set<Character> set = new HashSet<>();
            for(int i=0; i<2; i++){
                if (!pq.isEmpty()){
                    char c = pq.poll();
                    set.add(c);
                    sb.append(c);
                    map.put(c, map.get(c)-1);
                }
            }
            
            for (Character c:set){ //put char back to pq if cnt>1
                if (map.get(c) > 0) pq.offer(c);
            }
        }
        
        return sb.toString();
    }  
}