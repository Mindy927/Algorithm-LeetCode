/*
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:

Input: "bcabc"
Output: "abc"
Example 2:

Input: "cbacdcbc"
Output: "acdb"

Author:Mindy927*/


/*
(1)  int[26] cnt saves occurrences of 26 chars
(2)  boolean[26] used , push to stack when char i is not used
(3) bcd a c
   — push to stack for increased lexicographical order : bcd
  —  Decrease (encounter a  & !used[a] ) check whether we are safe to pop k based on cnt[k-‘a’] > 0
*/

class Solution {
    public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        boolean[] used = new boolean[26];
        for (int i=0; i<s.length(); i++){
            cnt[s.charAt(i)-'a']++;
        }
        
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            while (!stack.isEmpty() && stack.peek() > c && cnt[stack.peek()-'a'] > 0 &&!used[c-'a']) {
                    used[stack.peek()-'a'] = false;
                    stack.pop();
            } 
            
            if (!used[c - 'a'] ){
                stack.push(c);
                used[c - 'a'] = true;
            }
            cnt[c - 'a']--;
        }
       
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
