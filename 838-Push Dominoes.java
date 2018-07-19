/*
There are N dominoes in a line, and we place each domino vertically upright.

In the beginning, we simultaneously push some of the dominoes either to the left or to the right.



After each second, each domino that is falling to the left pushes the adjacent domino on the left.

Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.

Return a string representing the final state. 

Example 1:

Input: ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."
Example 2:

Input: "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.

Author: Mindy927 */

/*
1 pass, 2 pointers
convert original string to: 'L' + dominoes + ‘R'
For each R/L find next R/L : 4 cases
1. R…R ==> all Rs
2. L… L ==> all Ls
3. L..R ==> no change
4. R… L ==> two pointers,mid
*/
class Solution {
    public String pushDominoes(String dominoes) {
        String temp = 'L' + dominoes + 'R';
        char[] chars = temp.toCharArray();
        int i = 0;
        int j = i+1;
        while (chars[j]=='.') j++;
        
        while( j<chars.length){
            if (chars[i] == 'R' && chars[j] == 'R') {
                for (int k=i;k<=j;k++) chars[k] = 'R';
            }
            
            if (chars[i] == 'L' && chars[j] == 'L') {
                for (int k=i;k<=j;k++) chars[k] = 'L';
            }
            
            if (chars[i] == 'R' && chars[j] == 'L'){
                boolean odd = (j-i+1)%2 == 1;
                int mid = i + (j-i)/2;
                int left = odd? mid-1:mid;
                int right = mid+1;
                for (int k=i;k<=left;k++) chars[k] = 'R';
                for (int k=right;k<=j;k++) chars[k] = 'L';
            }
            i = j;
            j++;
            while (j<chars.length && chars[j]=='.') j++;
        }
        
        return String.valueOf(chars).substring(1,chars.length-1);
    
    }
}