/*
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output: 
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output: 
"a"

Author: Mindy927 */


class Solution {
    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, new Comparator<String>(){
            public int compare(String a, String b){
                if (a.length() != b.length()) return b.length() - a.length(); //longest
                else return a.compareTo(b); //smallest lexicographical order
            }
        });
        
        for (String str:d){
            if (isSubsequence(s, str)) return str;
        }
        return "";
    }
    
    
   public boolean isSubsequence(String s, String subSeq) { //delete chars in s to get subSet
       int i=0, j=0;
       while ( i<s.length() && j<subSeq.length()){
           if (s.charAt(i) == subSeq.charAt(j)) j++; 
           i++;
       }
       
       return j == subSeq.length();
    }
}