/*
Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

*/
//Method 1: indexOf
class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int index = -1;
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            index = t.indexOf(c, index+1); //next possilbe index of t matches cur char c starts from index+1
            if (index == -1) return false;
        }
        
        return index != -1;
    }
}

//Method 2 : Two pointers
class Solution {
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i<s.length() && j<t.length()){
            while (j<t.length() && s.charAt(i) != t.charAt(j)) j++;
            if (j==t.length()) return false;
            i++; j++;
        }
        
        return i==s.length();
    }
}

/*
Method 3: Binary Search
Map<char, List<Integer>> keep list of indices for each char
*/
class Solution {
    public boolean isSubsequence(String s, String t) {
        Map<Character, List<Integer>>  map = new HashMap<>();
        for (int i=0; i<t.length(); i++){
            char c = t.charAt(i);
            List<Integer> temp = map.containsKey(c)?  map.get(c):new ArrayList<>();
            temp.add(i);
            map.put(c, temp);
        }
        
        int prev = -1; //pointer for t
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if (!map.containsKey(c)) return false;
            List<Integer> list = map.get(c);
            int cur = binarySearch(list, prev, 0, list.size()-1);
            if (cur == -1) return false;
            prev = cur;
        }
        
        return true;
    }
    
    
    public int binarySearch(List<Integer> list, int prevIndex, int left, int right){ //get index > prevIndex
        while (left+1 < right){
            int mid = left + (right - left)/2;
            if (list.get(mid) <= prevIndex) left = mid;
            else right = mid;
        }
        
        return list.get(left) > prevIndex? list.get(left): list.get(right) > prevIndex? list.get(right):-1;
    }
}