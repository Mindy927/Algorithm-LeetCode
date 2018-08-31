/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

Author: Mindy927*/

//Method 1: DFS
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs(0, 0, n, "", result);
        return result;
    }
    
    public void dfs(int left, int right, int n, String temp, List<String> result){
        if (left == right && left == n) {
            result.add(temp);
            return;
        }
        
        if (left < n) dfs(left+1, right, n, temp+"(", result);
        if (right < left) dfs(left, right+1, n, temp+")", result);
    }
}

//Method 2: Iteration
/*
F(0) = ""
F(1) = "()"
F(n) = "(" + F(0) + ")" + F(n-1)
      + "(" + F(1) + ")" + F(n-2)
      +...
      + "(" + F(n-1) + ")" + F(0)
*/

class Solution {
    public List<String> generateParenthesis(int n) {
        List<List<String>> list = new ArrayList<>(); // i: F(i)
        list.add(Arrays.asList("")); //F(0)
        list.add(Arrays.asList("()")); //F(1);
        if (n < 2) return list.get(n);
        
        for (int i=2; i<=n; i++){
            List<String> temp = new ArrayList<>(); //F(i)
            for(int j=0; j<=i-1; j++){
                List<String> list1 = list.get(j); 
                List<String> list2 = list.get(i-1-j);
                //combine each F(j) with F(n-1-j);
                for (String str1:list1){
                    for (String str2:list2){
                        temp.add("(" + str1 + ")" + str2);
                    }
                }
            }
            list.add(temp);
        }
        
        return list.get(n);
    }
}