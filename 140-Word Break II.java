/*
Method 1: DFS


/*
Method 2: DP
dp[i]: a list of string that formed by s.substring[0,i]
*/

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> set = new HashSet<>();
        for(String word:wordDict) set.add(word);
        List<String>[] dp = new LinkedList[n];
        
        for(int i=0; i<s.length(); i++){
            List<String> list = new LinkedList<>();
            for (int j=0; j<=i; j++){
                String temp = s.substring(j, i+1);
                if (set.contains(temp)){
                    if (j==0) list.add(temp); //from start of string
                    else {
                        for (String str: dp[j-1]){ //combine str[j,i] with str[0,j-1]
                            list.add(str+" "+temp);
                        }
                    }
                }
            }
            dp[i] = list;
        }
        
        return dp[n-1];
    }
}