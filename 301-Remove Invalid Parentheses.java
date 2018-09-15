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
//Method 1 : Set + dfs
class Solution {
    public List<String> removeInvalidParentheses(String s) {      
        int left = 0, right = 0; //number of '(' and ')' to be removed
        int cnt = 0;
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if (c == '(') left++;
            if (c == ')'){
                if (left > 0) left--;
                else right++;
            }
        }
        
        Set<String> res = new HashSet<>();
        dfs(s, 0, left, right, 0, "", res);
        return new ArrayList<>(res);
    }
    
    public void dfs(String s, int start, int left, int right, int open, String temp, Set<String> res){
        //left < 0, delete more '(' than required, return
        //open < 0, temp string is not balanced now, e.g, "())", return
        if (left < 0 || right < 0 || open < 0) return;
        if (start == s.length()) {
            if (left == 0 && right == 0 && open==0) res.add(temp);
            return;
        }
        
        //We only consider 1 char (no for loop), left-1 indicating deleting 1 '(' in this round, and pass to next round
        char c = s.charAt(start);
        if ( c == '('){
            dfs(s, start+1, left-1, right, open, temp, res); //dont use '(' 
            dfs(s, start+1, left, right, open+1, temp + "(", res); //use '('
        }
        else if (c == ')'){
            dfs(s, start+1, left, right-1, open, temp, res); //dont use ')'
            dfs(s, start+1, left, right, open-1, temp + ")", res); //use ')'
        }else{
            dfs(s, start+1, left, right, open, temp + c, res);
        }
        
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