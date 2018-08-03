/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]


Author: Mindy927 */
//Method 1 : Set + Backtracking
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int left=0, right = 0; //number of '(' and ')' to be removed
        for (int i=0; i<s.length();i++){
            if (s.charAt(i) == '(') left++; 
            else if (s.charAt(i)==')'){
                if (left!=0) left--;
                else right++;
            }
        }
        
        Set<String> result = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        remove(s, 0, left, right, 0, sb, result);
        return new ArrayList<>(result);
    }
  
    public void remove(String s, int start, int left, int right, int open, StringBuilder sb, Set<String> result){
         if (left < 0 || right < 0 || open < 0 ) return;
         if (start == s.length() ){
            if (left == 0 && right ==0 && open==0) result.add(sb.toString());
            return;
        }
        
        char c = s.charAt(start);
        int len = sb.length();
        
        if (c =='('){
            remove(s, start+1, left-1, right, open, sb, result); //not use '(', dfs not use first, otherwise will append c to sb
            remove(s, start+1, left, right, open+1, sb.append(c), result);    //use '('
            
        }else if (c ==')'){
            remove(s, start+1, left, right-1, open, sb, result); //not use ')'
            remove(s, start+1, left, right, open-1, sb.append(c), result);    //use ')'
        } else{
           remove(s, start+1, left, right, open, sb.append(c), result);  
        }
        
        sb.setLength(len); //backtracking
    }
}



//Method 2
class Solution {
    //Key Idea: Can remove any of ')' in the prefix once more ')' appeared
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        remove(s,0,0,result,true);
        List<String> finalRes = new ArrayList<>();
        for (String temp:result){
            String reversed = new StringBuilder(temp).reverse().toString();
            remove(reversed,0,0,finalRes,false); //remove ')'
        }
        return finalRes;
    } 
    
    public void remove(String s, int start, int removeIndex, List<String> result, boolean left){ //true for '('
        char p1 = left? '(' : ')';
        char p2 = left? ')' : '(';
        
        int count = 0;
        for (int i=start; i<s.length(); i++){        
            if  (s.charAt(i) == p1)  count++;
            if  (s.charAt(i) == p2)  count--;
            if  (count >=0 ) continue;
            for (int j=removeIndex; j<=i; j++){ //remove exta ')' at pos j 
                if (s.charAt(j) == p2 && ( j== removeIndex || s.charAt(j-1) != p2)) {
                    //Not i+1 cause string in next round is shorter
                    remove(s.substring(0,j) + s.substring(j+1,s.length()), i, j, result, left); 
                }   
            }
            return; //Removed done for current start, need to start new counter in further DFS calls
        }    
      
       //Reach here when continue in for loops
       if (!left) result.add(new StringBuilder(s).reverse().toString());
       else result.add(s);
       return;
    }
}