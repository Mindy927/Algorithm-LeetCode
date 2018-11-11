/*
There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.

You can keep inputting the password, the password will automatically be matched against the last n digits entered.

For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.

Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted.

Example 1:
Input: n = 1, k = 2
Output: "01"
Note: "10" will be accepted too.
Example 2:
Input: n = 2, k = 2
Output: "00110"
Note: "01100", "10011", "11001" will be accepted too.
Note:
n will be in the range [1, 4].
k will be in the range [1, 10].
k^n will be at most 4096.


Author:Mindy927*/

class Solution {
    public String crackSafe(int n, int k) {
        Set<String> visited = new HashSet<>();
        double target = Math.pow(k,n); //total number of possible password
        StringBuilder sb = new StringBuilder();
        
        //start from 000...00
        for (int i=0; i<n; i++) sb.append("0");
        visited.add(sb.toString());
        
        dfs(n, k, target, visited, sb);
        return sb.toString();
    }
    
    //dfs for next digit based on visited set
    //return boolean since we could stop search when we found 1 solution
    public boolean dfs(int n, int k, double target,Set<String> visited, StringBuilder sb){
        if (visited.size() == target) return true;
          
        for (int i=0; i<k; i++){
            sb.append(""+i);
            String temp = sb.toString().substring(sb.length()-n, sb.length());
            if (!visited.contains(temp)){
                visited.add(temp);
                if (dfs(n, k, target, visited, sb)) return true;  
                visited.remove(temp); //revert set
            }
            sb.setLength(sb.length()-1); //revert sb
        }
        
        return false;
    }
}