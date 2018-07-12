/*
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

Author:Mindy927*/

class Solution {
    public String frequencySort(String s) {
        if (s.length()==0) return s;
        Map<Character, Integer> map = new HashMap<>(); //Char:cnt
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            map.put(c, map.containsKey(c)? map.get(c)+1:1);
        }
        
        PriorityQueue<Character> pq = new PriorityQueue<Character>(map.size(), new Comparator<Character>(){
            public int compare(Character a, Character b){
                return map.get(b) - map.get(a);   //max heap
            }
        });
                                                                   
       for (Character c:map.keySet()){
           pq.offer(c);
       }   
       
       StringBuilder sb = new StringBuilder();
       while (!pq.isEmpty()){
           char c = pq.poll();
           int cnt = map.get(c);
           while (cnt>0) {
               sb.append(c); cnt--;
           }
       }
                                   
      return sb.toString();
    }
}