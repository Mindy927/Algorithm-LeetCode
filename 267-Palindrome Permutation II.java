/*
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

Example 1:

Input: "aabb"
Output: ["abba", "baab"]
Example 2:

Input: "abc"
Output: []

Author: Mindy927
*/

class Solution {
    public List<String> generatePalindromes(String s) {
        int odd=0;
        String mid = "";
        Map<Character,Integer> map = new HashMap<>();
        List<Character> list = new ArrayList<>();
        List<String> result = new ArrayList<>();
        
        //Build Map to count chars
        char[] chars = s.toCharArray();
        for (int i=0; i<s.length(); i++){
            char c = chars[i];
            map.put(c, map.containsKey(c)? map.get(c)+1:1);
        }
        
        //Count odd chars, and add half of chars to list
        for (char c:map.keySet()){
            if (map.get(c)%2 != 0) {
                odd++; 
                mid = c + "";
            }
            for (int i=0; i<map.get(c)/2; i++){
                list.add(c);
            }
        }
        
        if (odd>1) return result;
        
        boolean[] used = new boolean[list.size()];
        dfs(list, used, new StringBuilder(), mid, result);
        return result;
    }
    
    public void dfs (List<Character> list, boolean[] used, StringBuilder sb, String mid, List<String> result){
        if (sb.length() == list.size() ) {
            StringBuilder second = new StringBuilder(sb);
            result.add(sb.toString() + mid + second.reverse().toString());
            return;
        }
        
        //For loop to find next possible char
        for (int i=0; i<used.length; i++){
            if (used[i]) continue;
            //add used[i] is same as add used[i-1] is last round, skip duplicate
            if (i>0 && list.get(i) == list.get(i-1) && !used[i-1]) continue; 
            sb.append(list.get(i));
            used[i] = true;
            dfs(list, used, sb, mid, result);
            used[i] = false;
            sb.setLength(sb.length()-1);
        }
    }
    
}