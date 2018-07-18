/*
Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.

Author:Mindy927*/

/*
find first digit which has larger digit after it, swap, break
dpR[i] stores index of max digit(last) in digits[i+1,n-1], find first digits[i] < digits[dpR[i]], swap
*/
class Solution {
    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int n = chars.length;
        int[] dpR = new int[n];
        
        dpR[n-1] = n-1;
        for (int i=n-2; i>=0; i--){
            dpR[i] = chars[dpR[i+1]] >= chars[i+1]? dpR[i+1]:i+1; // = handles case 1993, swap with second 9
        }
        
        for (int i=0; i<n-1; i++){
            if (chars[i] < chars[dpR[i]]) { // swap & break!
                char temp = chars[i];
                chars[i] = chars[dpR[i]];
                chars[dpR[i]] = temp;
                break;
            }
        }
        
        return Integer.parseInt(String.valueOf(chars));
    }
  
}