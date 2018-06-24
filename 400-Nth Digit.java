/*
Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Author: Mindy927 */

class Solution {
    public int findNthDigit(int n) {
        int len = 1; 
        long count = 9;
        int start = 1;
        
        //start: 1, 10, 100 ..
        //count: 9, 90, 900 ... 
        while ( n > len * count){
           n -= len * count;
           start *= 10;
           count *= 10;
           len++;
          
        }
        
        start += (n-1) / len;
        String s = Integer.toString(start);
		return Character.getNumericValue(s.charAt((n - 1) % len));

    }
}