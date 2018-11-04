/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

Example:

Input: low = "50", high = "100"
Output: 3 
Explanation: 69, 88, and 96 are three strobogrammatic numbers.
Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/

//find all possible solutions within length[low.length(), high.length()], verify
class Solution {
    public int strobogrammaticInRange(String low, String high) {
        int cnt = 0;
		List<String> res = new ArrayList<String>();
		for(int n = low.length(); n <= high.length(); n++){
			res.addAll(helper(n, n));
		}
        
		for(String num : res){ 
			if((num.length() == low.length()&&num.compareTo(low) < 0 ) ||(num.length() == high.length() && num.compareTo(high) > 0)) continue;
				cnt++;
		}
        
        return cnt;
    }
    
    //helper func with max to deal with case "0"
    public List<String> helper(int n, int max){
        if (n == 0) return new ArrayList<>(Arrays.asList(""));
        if (n == 1) return new ArrayList<>(Arrays.asList("0","1","8"));
        
        List<String> temp =  helper(n-2, max);
        List<String> res = new ArrayList<>();
        for (String str:temp){
            if (n != max) res.add("0" + str + "0");
            res.add("1" + str + "1");
            res.add("8" + str + "8");
            res.add("6" + str + "9");
            res.add("9" + str + "6");
        }
        
        return res;
    }
}