/*
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.

Author: Mindy927 */

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            List<String> temp = map.containsKey(key)? map.get(key):new ArrayList<>();
            temp.add(str);
            map.put(key, temp);
        }
        
        for (String key:map.keySet()){
            result.add(map.get(key));
        }
        
        return result;
    }
}