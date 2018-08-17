/*
Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

Example 1:

Input: 12
Output: 21
 

Example 2:

Input: 21
Output: -1

Author: Mindy927*/

class Solution {
    public int nextGreaterElement(int n) {
        String str = String.valueOf(n);
        
        //find first digit k at pos i that decrease
        int i = str.length()-2;
        while ( i>=0 ) {
            if (str.charAt(i) < str.charAt(i+1)) break; 
            i--;
        }
        
        //iterate from end, find first element at pos j > k, swap with k
        if ( i==-1 ) return -1;
        int j = str.length()-1;
        while ( j>i ){
            if (str.charAt(j) > str.charAt(i)) break;
            j--;
        }
        String temp = str.substring(i+1,j) + str.charAt(i) + str.substring(j+1); //[i, end]
        
        //reverse string after pos i
        StringBuilder sb = new StringBuilder(temp);
        sb.reverse();
        sb.insert(0,str.charAt(j));
        sb.insert(0, str.substring(0,i));
        
        long res = Long.parseLong(sb.toString());
        if (res > Integer.MAX_VALUE) return -1;
        return (int)res;
        
    }
}