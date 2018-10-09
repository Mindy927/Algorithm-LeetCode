/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output: 
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]

Author: Mindy927*/

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>(); //key: a list of strings with same key
        for (String str:strings){
            String key = buildKey(str);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(str);
        }
        
        List<List<String>> res = new ArrayList<>();
        for (String key:map.keySet()) res.add(map.get(key));
        return res;
    }
    
    //build key for each string based on diff of two adjacent chars
    public String buildKey(String str){
        String key = "";
        for (int i=0; i<str.length()-1; i++){
            int diff = str.charAt(i+1) > str.charAt(i)? str.charAt(i+1) - str.charAt(i):str.charAt(i+1) + 26 - str.charAt(i); 
            key += "-" + (char) diff;
        }
        return key;
    }
}